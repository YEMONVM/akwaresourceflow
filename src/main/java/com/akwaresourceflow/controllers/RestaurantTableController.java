package com.akwaresourceflow.controllers;

import com.akwaresourceflow.models.RestaurantTable;
import com.akwaresourceflow.services.Interfaces.RestaurantTableService;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/tables")
public class RestaurantTableController {

    @Autowired
    private RestaurantTableService restaurantTableService;

    @GetMapping
    public List<RestaurantTable> getAllTables() {
        return restaurantTableService.getAllTables();
    }

    @GetMapping("/{id}")
    public RestaurantTable getTableById(@PathVariable Long id) {
        return restaurantTableService.getTableById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Table not found with id " + id));
    }

    @PostMapping
    public RestaurantTable createTable(@RequestBody RestaurantTable restaurantTable) {
        return restaurantTableService.saveTable(restaurantTable);
    }

    @PutMapping("/{id}")
    public RestaurantTable updateTable(@PathVariable Long id, @RequestBody RestaurantTable restaurantTableDetails) throws ResourceNotFoundException {
        return restaurantTableService.updateTable(id, restaurantTableDetails);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTable(@PathVariable Long id) {
        restaurantTableService.deleteTable(id);
    }
}
