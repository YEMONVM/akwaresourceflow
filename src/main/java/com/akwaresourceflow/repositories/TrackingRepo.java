package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingRepo extends JpaRepository<Tracking,Long> {
}
