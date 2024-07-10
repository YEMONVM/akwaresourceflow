package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.RestaurantTable;
import com.akwaresourceflow.repositories.RestaurantTableRepo;
import com.akwaresourceflow.services.Interfaces.RestaurantTableService;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantTableServiceImpl implements RestaurantTableService {

    @Autowired
    private RestaurantTableRepo restaurantTableRepo;

    @Override
    public RestaurantTable createTable(RestaurantTable restaurantTable) {
        return restaurantTableRepo.save(restaurantTable);
    }

    @Override
    public Optional<RestaurantTable> getTableById(Long id) {
        return restaurantTableRepo.findById(id);
    }

    @Override
    public RestaurantTable saveTable(RestaurantTable restaurantTable) {
        return restaurantTableRepo.save(restaurantTable);
    }

    @Override
    public RestaurantTable updateTable(Long id, RestaurantTable restaurantTableDetails) throws ResourceNotFoundException {
        Optional<RestaurantTable> restaurantTableOptional = restaurantTableRepo.findById(id);
        if (restaurantTableOptional.isPresent()) {
            RestaurantTable existingTable = restaurantTableOptional.get();
            existingTable.setTableNumber(restaurantTableDetails.getTableNumber());
            existingTable.setCapacity(restaurantTableDetails.getCapacity());
            existingTable.setStatus(restaurantTableDetails.getStatus());
            return restaurantTableRepo.save(existingTable);
        } else {
            throw new ResourceNotFoundException("Table not found with id: " + id);
        }
    }

    @Override
    public void deleteTable(Long id) {
        Optional<RestaurantTable> restaurantTableOptional = restaurantTableRepo.findById(id);
        if (restaurantTableOptional.isPresent()) {
            restaurantTableRepo.delete(restaurantTableOptional.get());
        } else {
            throw new RuntimeException("Table not found with id: " + id);
        }
    }

    @Override
    public List<RestaurantTable> getAllTables() {
        return restaurantTableRepo.findAll();
    }
}
