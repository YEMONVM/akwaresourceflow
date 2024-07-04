package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.Vehicle;
import java.util.List;

public interface VehicleService {
    List<Vehicle> getAllVehicles();
    Vehicle saveVehicle(Vehicle vehicle);
    void deleteVehicle(Long id);

    Vehicle updateVehicle(Long id, Vehicle vehicleDetails);

    Vehicle updateVehicleStatus(Long id, String status);
}
