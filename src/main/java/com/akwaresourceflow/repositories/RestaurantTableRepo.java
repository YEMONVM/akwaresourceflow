package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTableRepo extends JpaRepository<RestaurantTable, Long> {
}
