package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.enums.TableStatus;
import com.akwaresourceflow.models.Restaurant;
import com.akwaresourceflow.models.RestaurantTable;
import com.akwaresourceflow.repositories.RestaurantRepo;
import com.akwaresourceflow.services.Interfaces.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Override
    @Transactional
    public Restaurant createRestaurant(Restaurant restaurant) {
        // Initialize the set of tables if it's not already initialized
        if (restaurant.getRestaurantTables() == null) {
            restaurant.setRestaurantTables(new HashSet<>());
        }

        // Associate each table with the restaurant and calculate totals
        for (RestaurantTable table : restaurant.getRestaurantTables()) {
            table.setRestaurant(restaurant);
        }

        // Save the restaurant with its tables
        return restaurantRepo.save(restaurant);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> listRestaurants = restaurantRepo.findAllWithTables();
        if (!listRestaurants.isEmpty()) {
            log.info("Restaurants list retrieved!");
            return listRestaurants;
        } else {
            log.warn("No restaurants found!");
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public Restaurant updateRestaurant(Long id, Restaurant restaurantDetails) {
        return restaurantRepo.findById(id).map(restaurant -> {
            restaurant.setName(restaurantDetails.getName());
            restaurant.setPhonenumber(restaurantDetails.getPhonenumber());
            restaurant.getRestaurantTables().clear();
            restaurant.getRestaurantTables().addAll(restaurantDetails.getRestaurantTables());
            restaurant.getRestaurantTables().forEach(table -> table.setRestaurant(restaurant));
            return restaurantRepo.save(restaurant);
        }).orElseThrow(() -> new RuntimeException("Restaurant not found with id " + id));
    }

    @Override
    @Transactional
    public void deleteRestaurant(Long id) {
        restaurantRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Restaurant> getAllRestaurantDetails() {
        List<Restaurant> restaurants = restaurantRepo.findAllWithTables();

        for (Restaurant restaurant : restaurants) {
            Set<RestaurantTable> tables = restaurant.getRestaurantTables();

            if (tables == null || tables.isEmpty()) {
                log.warn("Restaurant ID: {}, restaurantTables is null or empty. Fetching via native query.", restaurant.getId());

                // Fetch using the native query
                List<Object[]> results = restaurantRepo.findRestaurantAndTablesNative(restaurant.getId());
                tables = results.stream()
                        .map(result -> {
                            RestaurantTable table = new RestaurantTable();
                            table.setId((Long) result[3]);  // Correctly casting to Long

                            // Handle null value for capacity, defaulting to a minimum positive value
                            Integer capacity = result[4] != null && (Integer) result[4] > 0 ? (Integer) result[4] : 1;
                            table.setCapacity(capacity);

                            // Handle null value for status, assuming that a null status should be treated as a default value
                            TableStatus status = result[5] != null ? TableStatus.fromValue((Short) result[5]) : TableStatus.AVAILABLE;
                            table.setStatus(status);

                            table.setTableNumber(result[6] != null ? (Integer) result[6] : 0);
                            table.setRestaurant(restaurant);
                            return table;
                        })
                        .collect(Collectors.toSet());

                restaurant.setRestaurantTables(tables);
            }

            if (tables != null && !tables.isEmpty()) {
                log.info("Restaurant: {} has {} tables", restaurant.getName(), tables.size());
            } else {
                log.warn("Still no tables found after re-fetching for Restaurant ID: {}", restaurant.getId());
            }

            int totalTableNumber = restaurant.getTotalTableNumber();
            int totalCapacity = restaurant.getTotalCapacity();
            log.info("Restaurant ID: {}, Total Tables: {}, Total Capacity: {}", restaurant.getId(), totalTableNumber, totalCapacity);
        }

        return restaurants;
    }


    @Override
    @Transactional(readOnly = true)
    public Restaurant getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepo.findByIdWithTables(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Hibernate.initialize(restaurant.getRestaurantTables()); // Explicitly initialize lazy collection
        log.info("Restaurant: {} has {} tables", restaurant.getName(), restaurant.getRestaurantTables().size());
        return restaurant;
    }

    @Override
    @Transactional
    public void testRestaurantTables(Long id) {
        List<Object[]> result = restaurantRepo.findRestaurantWithTables(id);
        for (Object[] row : result) {
            Restaurant restaurant = (Restaurant) row[0];
            RestaurantTable table = (RestaurantTable) row[1];
            log.info("Restaurant: {} has table: {}, Capacity: {}", restaurant.getName(), table.getTableNumber(), table.getCapacity());
        }
    }
}
