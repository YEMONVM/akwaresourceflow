package com.akwaresourceflow.controllers;

import com.akwaresourceflow.models.Station;
import com.akwaresourceflow.services.Interfaces.StationService;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping
    public List<Station> getAllStations() {
        return stationService.getAllStations();
    }

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
}
