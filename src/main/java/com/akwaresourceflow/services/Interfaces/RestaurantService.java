package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.Restaurant;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    //getAll+getbyID+save+update+delete

    List<Restaurant> getAllRestaurants();
    Restaurant updateRestaurant(Long id, Restaurant restaurantDetails) throws ResourceNotFoundException;
    void deleteRestaurant(Long id);

    // New method to get RestaurantDTO list
    List<Restaurant> getAllRestaurantDetails();

    @Transactional(readOnly = true)
    Restaurant getRestaurantById(Long id);

    void testRestaurantTables(Long id);
    Restaurant createRestaurant(Restaurant restaurant);

}
