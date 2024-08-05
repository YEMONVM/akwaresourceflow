package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.Location;

import java.util.List;

public interface LocationService {
    void saveLocation(Long vehicleId, Location location);

    List<Location> getLocationHistory(String vehicleId);
}
