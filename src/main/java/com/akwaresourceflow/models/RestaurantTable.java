package com.akwaresourceflow.models;

import com.akwaresourceflow.enums.TableStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int tableNumber;
    private int capacity;
    private TableStatus status;


    @OneToMany(mappedBy = "restaurantTable")
    private List<Bill> bills;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
