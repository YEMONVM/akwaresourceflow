package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.Station;
import com.akwaresourceflow.repositories.StationRepo;
import com.akwaresourceflow.services.Interfaces.StationService;
import com.akwaresourceflow.dto.StationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StationServiceImpl implements StationService  {
    @Autowired
    private StationRepo stationRepo;

    @Override
    @Cacheable(value = "stationsCache", key = "#page + '-' + #pageSize")
    public List<StationDTO> getAllStations(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        Page<Station> stationPage = stationRepo.findAll(pageable);
        return stationPage.getContent().stream()
            .map(station -> new StationDTO(station.getId(), station.getName(), station.getAddress(), station.getCity(), station.getPhonenumber()))
            .collect(Collectors.toList());
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

    @Override
    @Cacheable(value = "stationCountCache")
    public long getTotalStationCount() {
        return stationRepo.count();
    }
}