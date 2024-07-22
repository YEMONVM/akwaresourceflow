package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.repositories.AppUserRepo;
import com.akwaresourceflow.repositories.LocationRepo;
import com.akwaresourceflow.services.Interfaces.LocationService;

public class LocationServiceImpl implements LocationService {
    private LocationRepo locationRepo;

    public LocationServiceImpl(LocationRepo locationRepo) {
        this.locationRepo = locationRepo;
    }
}
