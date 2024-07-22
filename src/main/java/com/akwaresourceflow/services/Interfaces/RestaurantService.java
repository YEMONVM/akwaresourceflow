package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.AppUser;
import com.akwaresourceflow.models.Restaurant;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    //getAll+getbyID+save+update+delete

    List<Restaurant> getAllRestaurants();
    Restaurant updateRestaurant(Long id, Restaurant restaurantDetails) throws ResourceNotFoundException;
    void deleteRestaurant(Long id);
}
