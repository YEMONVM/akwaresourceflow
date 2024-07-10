package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.RestaurantTable;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface RestaurantTableService {
    List<RestaurantTable> getAllTables();
    RestaurantTable createTable(RestaurantTable restaurantTable);
    Optional<RestaurantTable> getTableById(Long id);
    RestaurantTable saveTable(RestaurantTable diningTable);
    RestaurantTable updateTable(Long id, RestaurantTable diningTableDetails) throws ResourceNotFoundException;
    void deleteTable(Long id);
}
