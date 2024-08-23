package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.Station;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StationService {
    Page<Station> getAllStations(Pageable pageable);
    Optional<Station> getStationById(Long id);
    Station saveStation(Station station);
    Station updateStation(Long id, Station stationDetails) throws ResourceNotFoundException;
    void deleteStation(Long id);
}

