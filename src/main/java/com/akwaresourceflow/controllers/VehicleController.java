package com.akwaresourceflow.controllers;

import com.akwaresourceflow.models.Vehicle;
import com.akwaresourceflow.services.Interfaces.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found with id " + id));
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicleDetails) {
        return vehicleService.updateVehicle(id, vehicleDetails);
    }

    @PutMapping("/{id}/status")
    public Vehicle updateVehicleStatus(@PathVariable Long id, @RequestBody String status) {
        return vehicleService.updateVehicleStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }
}
