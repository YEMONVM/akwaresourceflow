package com.akwaresourceflow.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Slf4j
@EqualsAndHashCode(exclude = "restaurantTables")
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phonenumber;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude
    private Set<RestaurantTable> restaurantTables;

    // Calculate totalTableNumber dynamically
    public int getTotalTableNumber() {
        if (restaurantTables == null || restaurantTables.isEmpty()) {
            log.warn("Restaurant ID: {}, restaurantTables is null or empty", this.id);
            return 0;
        }
        int totalTableNumber = restaurantTables.size();
        log.info("Restaurant ID: {}, Total Tables: {}", this.id, totalTableNumber);
        return totalTableNumber;
    }

    // Calculate totalCapacity dynamically
    public int getTotalCapacity() {
        if (restaurantTables == null || restaurantTables.isEmpty()) {
            log.warn("Restaurant ID: {}, restaurantTables is null or empty", this.id);
            return 0;
        }
        int totalCapacity = restaurantTables.stream().mapToInt(RestaurantTable::getCapacity).sum();
        log.info("Restaurant ID: {}, Total Capacity: {}", this.id, totalCapacity);
        return totalCapacity;
    }

    // Prevent setting totalTableNumber and totalCapacity directly
    public void setTotalTableNumber(int totalTableNumber) {
        // No-op or throw exception if needed
    }

    public void setTotalCapacity(int totalCapacity) {
        // No-op or throw exception if needed
    }
}
