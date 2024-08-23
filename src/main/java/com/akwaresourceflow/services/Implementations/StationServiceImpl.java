package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.Station;
import com.akwaresourceflow.repositories.StationRepo;
import com.akwaresourceflow.services.Interfaces.StationService;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@Slf4j
public class StationServiceImpl implements StationService  {
    @Autowired
    private StationRepo stationRepo;

    @Override
    public Page<Station> getAllStations(Pageable pageable) {
        Page<Station> stations = stationRepo.findAll(pageable);
        log.debug("Fetched {} stations", stations.getTotalElements());
        return stations;
    }


    @Override
    public Optional<Station> getStationById(Long id) {
        try {
            return stationRepo.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch station with id " + id, e);
        }
    }

    @Override
    public Station saveStation(Station station) {
        try {
            return stationRepo.save(station);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save station", e);
        }
    }

    @Override
    public Station updateStation(Long id, Station stationDetails) throws ResourceNotFoundException {
        try {
            return stationRepo.findById(id).map(station -> {
                station.setName(stationDetails.getName());
                station.setAddress(stationDetails.getAddress());
                station.setCity(stationDetails.getCity());
                station.setPhonenumber(stationDetails.getPhonenumber());
                return stationRepo.save(station);
            }).orElseThrow(() -> new ResourceNotFoundException("Station not found with id " + id));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update station with id " + id, e);
        }
    }

    @Override
    public void deleteStation(Long id) {
        try {
            stationRepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete station with id " + id, e);
        }
    }
}