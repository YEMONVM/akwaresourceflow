package com.akwaresourceflow.controllers;


import com.akwaresourceflow.models.AppUser;
import com.akwaresourceflow.models.Restaurant;
import com.akwaresourceflow.services.Interfaces.RestaurantService;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurantDetails) throws ResourceNotFoundException {
        return restaurantService.updateRestaurant(id, restaurantDetails);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }


    @GetMapping("/details")
    public ResponseEntity<List<Restaurant>> getAllRestaurantDetails() {
        List<Restaurant> restaurantDetails = restaurantService.getAllRestaurantDetails();
        return ResponseEntity.ok(restaurantDetails);
    }
    @GetMapping("/test/{id}")
    public ResponseEntity<Void> testRestaurantTables(@PathVariable Long id) {
        restaurantService.testRestaurantTables(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant);
    }
}
