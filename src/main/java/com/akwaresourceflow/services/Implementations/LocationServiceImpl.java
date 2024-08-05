package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.Location;
import com.akwaresourceflow.repositories.AppUserRepo;
import com.akwaresourceflow.repositories.LocationRepo;
import com.akwaresourceflow.services.Interfaces.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LocationServiceImpl implements LocationService {
    private LocationRepo locationRepo;

    public LocationServiceImpl(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }

    @Override
    public void saveLocation(Long vehicleId, Location location) {

    }

    @Override
    public List<Location> getLocationHistory(String vehicleId) {
        return null;
    }
}
