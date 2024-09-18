package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.dto.StationDTO;
import com.akwaresourceflow.models.Station;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface StationService {
    List<StationDTO> getAllStations(int page, int pageSize);
    long getTotalStationCount();
    Optional<Station> getStationById(Long id);
    Station saveStation(Station station);
    Station updateStation(Long id, Station stationDetails) throws ResourceNotFoundException;
    void deleteStation(Long id);
}

