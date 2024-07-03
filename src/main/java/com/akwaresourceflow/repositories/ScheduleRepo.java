package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule,Long> {
}
