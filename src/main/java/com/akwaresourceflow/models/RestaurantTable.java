package com.akwaresourceflow.models;

import com.akwaresourceflow.enums.TableStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(exclude = "restaurant")
@Table(name = "restaurant_table")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int tableNumber;
    private int capacity;

    @Enumerated(EnumType.ORDINAL)
    private TableStatus status;

    @OneToMany(mappedBy = "restaurantTable")
    @JsonManagedReference
    private List<Bill> bills;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference
    @ToString.Exclude
    private Restaurant restaurant;

    // Add validations for tableNumber and capacity
    public void setTableNumber(int tableNumber) {
        if (tableNumber < 0) {
            throw new IllegalArgumentException("Table number must be a positive integer.");
        }
        this.tableNumber = tableNumber;
    }

    public void setCapacity(Integer capacity) {
        if (capacity == null || capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be a positive integer.");
        }
        this.capacity = capacity;
    }

}
