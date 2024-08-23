package com.akwaresourceflow.controllers;

import com.akwaresourceflow.models.Station;
import com.akwaresourceflow.services.Interfaces.StationService;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping("/{id}")
    public Station getStationById(@PathVariable Long id) {
        return stationService.getStationById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Station not found with id " + id));
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
    public ResponseEntity<Page<Station>> getAllStations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Page<Station> stations = (Page<Station>) stationService.getAllStations(PageRequest.of(page, size));
            return ResponseEntity.ok(stations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
