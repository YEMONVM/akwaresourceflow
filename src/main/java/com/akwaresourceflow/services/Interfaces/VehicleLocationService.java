package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.Location;
import com.akwaresourceflow.models.Vehicle;

import java.util.List;

public interface VehicleLocationService {
    void saveLocation(Long vehicleId, Location location);
    List<Location> getLocationHistory(Long vehicleId);
    Vehicle getVehicle(Long vehicleId);
    void saveVehicle(Vehicle vehicle);
}
