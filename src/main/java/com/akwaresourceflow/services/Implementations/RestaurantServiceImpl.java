package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.AppUser;
import com.akwaresourceflow.models.Restaurant;
import com.akwaresourceflow.repositories.RestaurantRepo;
import com.akwaresourceflow.services.Interfaces.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;


    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> listRestaurants =restaurantRepo.findAll();
        if (!listRestaurants.isEmpty()){
            log.info("Restaurants list retrieved!");
            return listRestaurants;
        }
        else {
            log.warn("No restaurants found!");
        }
        return Collections.emptyList();
    }

    @Override
    public Restaurant updateRestaurant(Long id, Restaurant restaurantDetails) throws ResourceNotFoundException {
        try {
            return restaurantRepo.findById(id).map(restaurant -> {
                restaurant.setName(restaurantDetails.getName());
                restaurant.setPhonenumber(restaurantDetails.getPhonenumber());
                return restaurantRepo.save(restaurant);
            }).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + id));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update restaurant with id " + id, e);
        }
    }

    @Override
    public void deleteRestaurant(Long id) {
        try {
            restaurantRepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete restaurant with id " + id, e);
        }
    }
}