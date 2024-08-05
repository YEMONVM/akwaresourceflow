package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingRepo extends JpaRepository<Tracking, Long> {
    List<Tracking> findByVehicleId(Long vehicleId); // Use Long for vehicleId
}
