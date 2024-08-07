package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.Schedule;
import com.akwaresourceflow.models.Task;
import com.akwaresourceflow.repositories.ScheduleRepo;
import com.akwaresourceflow.repositories.TaskRepo;
import com.akwaresourceflow.services.Interfaces.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepo scheduleRepo;
    private final TaskRepo taskRepo;

    public ScheduleServiceImpl(ScheduleRepo scheduleRepo, TaskRepo taskRepo) {
        this.scheduleRepo = scheduleRepo;
        this.taskRepo = taskRepo;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        log.info("Fetching all schedules");
        return scheduleRepo.findAll();
    }

    @Override
    public Schedule getSchedule(Long id) {
        log.info("Fetching schedule with id: {}", id);
        Optional<Schedule> schedule = scheduleRepo.findById(id);
        if (schedule.isPresent()) {
            return schedule.get();
        } else {
            log.warn("Schedule with id: {} not found", id);
            throw new RuntimeException("Schedule not found");
        }
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        log.info("Saving new schedule: {}", schedule);
        try {
            Set<Task> managedTasks = schedule.getTasks().stream()
                    .map(task -> taskRepo.findById(task.getId())
                            .orElseThrow(() -> new RuntimeException("Task not found: " + task.getId())))
                    .collect(Collectors.toSet());
            schedule.setTasks(managedTasks);
            return scheduleRepo.save(schedule);
        } catch (Exception e) {
            log.error("Error saving schedule: {}", schedule, e);
            throw new RuntimeException("Error saving schedule");
        }
    }

    @Override
    public void deleteSchedule(Long id) {
        log.info("Deleting schedule with id: {}", id);
        if (scheduleRepo.existsById(id)) {
            try {
                scheduleRepo.deleteById(id);
                log.info("Schedule with id: {} deleted successfully", id);
            } catch (Exception e) {
                log.error("Error deleting schedule with id: {}", id, e);
                throw new RuntimeException("Error deleting schedule");
            }
        } else {
            log.warn("Schedule with id: {} not found, deletion skipped", id);
            throw new RuntimeException("Schedule not found");
        }
    }

    @Override
    public Schedule updateSchedule(Long id, Schedule updatedSchedule) {
        log.info("Updating schedule with id: {}", id);

        Optional<Schedule> optionalSchedule = scheduleRepo.findById(id);
        if (optionalSchedule.isPresent()) {
            Schedule existingSchedule = optionalSchedule.get();
            log.debug("Existing schedule found: {}", existingSchedule);

            existingSchedule.setStartdate(updatedSchedule.getStartdate());
            existingSchedule.setEnddate(updatedSchedule.getEnddate());

            // Clear existing tasks and add updated tasks
            existingSchedule.getTasks().clear();
            log.debug("Cleared existing tasks for schedule with id: {}", id);

            for (Task task : updatedSchedule.getTasks()) {
                log.debug("Processing task with id: {}", task.getId());
                Task managedTask = taskRepo.findById(task.getId())
                        .orElseThrow(() -> new RuntimeException("Task not found: " + task.getId()));
                existingSchedule.getTasks().add(managedTask);
                log.debug("Added task with id: {} to schedule with id: {}", task.getId(), id);
            }

            try {
                Schedule savedSchedule = scheduleRepo.save(existingSchedule);
                log.info("Schedule with id: {} successfully updated", id);
                return savedSchedule;
            } catch (Exception e) {
                log.error("Error updating schedule with id: {}", id, e);
                throw new RuntimeException("Error updating schedule");
            }
        } else {
            log.warn("Schedule with id: {} not found, update skipped", id);
            throw new RuntimeException("Schedule not found");
        }
    }

}
