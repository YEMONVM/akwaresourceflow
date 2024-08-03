package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.Location;
import com.akwaresourceflow.models.Tracking;
import com.akwaresourceflow.models.Vehicle;
import com.akwaresourceflow.services.Interfaces.VehicleLocationService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VehicleLocationServiceImpl implements VehicleLocationService {

    private final Map<Long, Vehicle> vehicles = new HashMap<>();
    private final Map<Long, List<Location>> locationHistories = new HashMap<>();

    @Override
    public void saveLocation(Long vehicleId, Location location) {
        Vehicle vehicle = vehicles.computeIfAbsent(vehicleId, k -> new Vehicle());
        vehicle.setId(vehicleId);
        vehicle.setLocation(location);

        List<Location> locations = locationHistories.computeIfAbsent(vehicleId, k -> new ArrayList<>());
        locations.add(location);

        // Simulate persisting the entities
        vehicles.put(vehicleId, vehicle);
        locationHistories.put(vehicleId, locations);
    }

    @Override
    public List<Location> getLocationHistory(Long vehicleId) {
        return locationHistories.getOrDefault(vehicleId, Collections.emptyList());
    }

    @Override
    public Vehicle getVehicle(Long vehicleId) {
        return vehicles.get(vehicleId);
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getId(), vehicle);
    }
}
