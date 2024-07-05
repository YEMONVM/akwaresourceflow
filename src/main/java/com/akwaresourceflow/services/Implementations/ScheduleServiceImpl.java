package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.Schedule;
import com.akwaresourceflow.repositories.ScheduleRepo;
import com.akwaresourceflow.services.Interfaces.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepo scheduleRepo;

    public ScheduleServiceImpl(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepo.findAll();
    }

    @Override
    public Schedule getSchedule(Long id) {
        return scheduleRepo.findById(id).orElse(null);
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepo.save(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepo.deleteById(id);
    }

    @Override
    public void updateSchedule(Long id, Schedule schedule) {
        if (scheduleRepo.existsById(id)) {
            schedule.setId(id);
            scheduleRepo.save(schedule);
        }
    }
}
