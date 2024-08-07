package com.akwaresourceflow.controllers;

import com.akwaresourceflow.models.Schedule;
import com.akwaresourceflow.services.Interfaces.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/schedules")
public class ScheduleController {
    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        try {
            List<Schedule> schedules = scheduleService.getAllSchedules();
            return new ResponseEntity<>(schedules, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching schedules", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching schedules");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable Long id) {
        try {
            Schedule schedule = scheduleService.getSchedule(id);
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Error fetching schedule with id: {}", id, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        }
    }

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        logger.debug("Creating schedule: {}", schedule);
        try {
            Schedule createdSchedule = scheduleService.saveSchedule(schedule);
            return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating schedule", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating schedule");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody Schedule schedule) {
        logger.debug("Updating schedule: {}", schedule);
        try {
            Schedule updatedSchedule = scheduleService.updateSchedule(id, schedule);
            return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Error updating schedule with id: {}", id, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        } catch (Exception e) {
            logger.error("Error updating schedule with id: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating schedule");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        try {
            scheduleService.deleteSchedule(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            logger.error("Error deleting schedule with id: {}", id, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule not found");
        } catch (Exception e) {
            logger.error("Error deleting schedule with id: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting schedule");
        }
    }
}
