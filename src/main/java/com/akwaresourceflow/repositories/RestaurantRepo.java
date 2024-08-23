package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.restaurantTables")
    List<Restaurant> findAllWithTables();

    @Query("SELECT r, t FROM Restaurant r JOIN r.restaurantTables t WHERE r.id = :id")
    List<Object[]> findRestaurantWithTables(@Param("id") Long id);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.restaurantTables WHERE r.id = :id")
    Optional<Restaurant> findByIdWithTables(@Param("id") Long id);

    @Query(value = "SELECT r.id, r.name, r.phonenumber, rt.id, rt.capacity, rt.status, rt.table_number FROM restaurant r LEFT JOIN restaurant_table rt ON r.id = rt.restaurant_id WHERE r.id = :id", nativeQuery = true)
    List<Object[]> findRestaurantAndTablesNative(@Param("id") Long id);
}
