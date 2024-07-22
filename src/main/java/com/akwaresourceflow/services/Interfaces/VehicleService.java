package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.enums.VehicleStatus;
import com.akwaresourceflow.models.Vehicle;
import java.util.List;
import java.util.Optional;

public interface VehicleService {
    List<Vehicle> getAllVehicles();
    Vehicle saveVehicle(Vehicle vehicle);
    void deleteVehicle(Long id);
    Vehicle updateVehicle(Long id, Vehicle vehicleDetails);
    Vehicle updateVehicleStatus(Long id, VehicleStatus status);
    Optional<Vehicle> getVehicleById(Long id);
}
