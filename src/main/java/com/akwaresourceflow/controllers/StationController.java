package com.akwaresourceflow.controllers;

import com.akwaresourceflow.models.Station;
import com.akwaresourceflow.services.Interfaces.StationService;
import com.akwaresourceflow.dto.StationDTO;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpHeaders;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/api/stations")
@Slf4j
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("/{id}")
    public ResponseEntity<Station> getStationById(@PathVariable Long id) {
        try {
            return stationService.getStationById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            log.error("Error fetching station with id " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public Station createStation(@RequestBody Station station) {
        return stationService.saveStation(station);
    }

    @PutMapping("/{id}")
    public Station updateStation(@PathVariable Long id, @RequestBody Station stationDetails) throws ResourceNotFoundException {
        return stationService.updateStation(id, stationDetails);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStation(@PathVariable Long id) {
        stationService.deleteStation(id);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllStations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<StationDTO> stations = stationService.getAllStations(page, size);
            long totalCount = stationService.getTotalStationCount();
            Map<String, Object> response = new HashMap<>();
            response.put("stations", stations);
            response.put("totalCount", totalCount);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching stations", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
