package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.enums.VehicleStatus;
import com.akwaresourceflow.models.Vehicle;
import com.akwaresourceflow.repositories.VehicleRepo;
import com.akwaresourceflow.services.Interfaces.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepo.save(vehicle);
    }

    @Override
    public void deleteVehicle(Long id) {
        try {
            vehicleRepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete vehicle with id " + id, e);
        }
    }

    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        return vehicleRepo.findById(id).map(vehicle -> {
            vehicle.setType(vehicleDetails.getType());
            vehicle.setCurrentLocation(vehicleDetails.getCurrentLocation());
            vehicle.setCapacity(vehicleDetails.getCapacity());
            vehicle.setLatitude(vehicleDetails.getLatitude());
            vehicle.setLongitude(vehicleDetails.getLongitude());
            vehicle.setStatus(vehicleDetails.getStatus());
            return vehicleRepo.save(vehicle);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id " + id));
    }

    @Override
    public Vehicle updateVehicleStatus(Long id, VehicleStatus status) {
         return vehicleRepo.findById(id).map(vehicle -> {
            vehicle.setStatus(status);
            return vehicleRepo.save(vehicle);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id " + id));
    }


    @Override
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepo.findById(id);
    }
}
