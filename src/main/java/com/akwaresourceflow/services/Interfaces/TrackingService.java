package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.Tracking;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TrackingService {
    List<Tracking> getAllTrackings();
    Optional<Tracking> getTrackingById(Long id);
    Tracking saveTracking(Tracking tracking);
    Tracking updateTracking(Long id, Tracking trackingDetails) throws ResourceNotFoundException;
    void deleteTracking(Long id);
    List<Tracking> getTrackingByVehicleId(Long vehicleId);
}
