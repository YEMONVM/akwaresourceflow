package com.akwaresourceflow.controllers;

import com.akwaresourceflow.models.Location;
import com.akwaresourceflow.services.Interfaces.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping("/location")
    public ResponseEntity<Void> updateLocation(@RequestParam Long vehicleId, @RequestBody Location location) {
        locationService.saveLocation(vehicleId, location);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/history/{vehicleId}")
    public ResponseEntity<List<Location>> getLocationHistory(@PathVariable String vehicleId) {
        List<Location> history = locationService.getLocationHistory(vehicleId);
        return ResponseEntity.ok(history);
    }
}
