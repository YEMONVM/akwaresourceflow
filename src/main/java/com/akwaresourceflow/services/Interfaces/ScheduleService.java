package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getAllSchedules();
    Schedule getSchedule(Long id);
    Schedule saveSchedule(Schedule schedule);
    void deleteSchedule(Long id);
    void updateSchedule(Long id, Schedule schedule);
}
