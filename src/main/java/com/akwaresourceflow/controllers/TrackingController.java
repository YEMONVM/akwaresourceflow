package com.akwaresourceflow.controllers;

import com.akwaresourceflow.models.Tracking;
import com.akwaresourceflow.services.Interfaces.TrackingService;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping
    public List<Tracking> getAllTrackings() {
        return trackingService.getAllTrackings();
    }

    @GetMapping("/{id}")
    public Optional<Tracking> getTrackingById(@PathVariable Long id) {
        return trackingService.getTrackingById(id);
    }

    @PostMapping
    public Tracking createTracking(@RequestBody Tracking tracking) {
        return trackingService.saveTracking(tracking);
    }

    @PutMapping("/{id}")
    public Tracking updateTracking(@PathVariable Long id, @RequestBody Tracking trackingDetails) throws ResourceNotFoundException {
        return trackingService.updateTracking(id, trackingDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTracking(@PathVariable Long id) {
        trackingService.deleteTracking(id);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<Tracking> getTrackingByVehicleId(@PathVariable Long vehicleId) {
        return trackingService.getTrackingByVehicleId(vehicleId);
    }
}
