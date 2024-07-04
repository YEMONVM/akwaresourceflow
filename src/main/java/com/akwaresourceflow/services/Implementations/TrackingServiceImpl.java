package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.Tracking;
import com.akwaresourceflow.repositories.TrackingRepo;
import com.akwaresourceflow.services.Interfaces.TrackingService;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TrackingServiceImpl implements TrackingService {

    @Autowired
    private TrackingRepo trackingRepo;

    @Override
    public List<Tracking> getAllTrackings() {
        return trackingRepo.findAll();
    }

    @Override
    public Optional<Tracking> getTrackingById(Long id) {
        return trackingRepo.findById(id);
    }

    @Override
    public Tracking saveTracking(Tracking tracking) {
        return trackingRepo.save(tracking);
    }

    @Override
    public Tracking updateTracking(Long id, Tracking trackingDetails) throws ResourceNotFoundException {
        try {
            return trackingRepo.findById(id).map(tracking -> {
                tracking.setVehicleId(trackingDetails.getVehicleId());
                tracking.setLocationData(trackingDetails.getLocationData());
                tracking.setTimestamp(trackingDetails.getTimestamp());
                tracking.setSpeed(trackingDetails.getSpeed());
                return trackingRepo.save(tracking);
            }).orElseThrow(() -> new ResourceNotFoundException("Tracking not found with id " + id));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update tracking with id " + id, e);
        }
    }

    @Override
    public void deleteTracking(Long id) {
        try {
            trackingRepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete tracking with id " + id, e);
        }
    }

    @Override
    public List<Tracking> getTrackingByVehicleId(Long vehicleId) {
        return trackingRepo.findByVehicleId(vehicleId);
    }
}
