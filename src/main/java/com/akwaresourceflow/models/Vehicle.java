package com.akwaresourceflow.models;

import com.akwaresourceflow.enums.VehicleStatus;
import com.akwaresourceflow.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private VehicleType type;
    private String currentLocation;
    private int capacity;
    private String destination;
    private double latitude;
    private double longitude;
    private VehicleStatus status; // Available, In Transit, Under Maintenance, etc.

    @ManyToMany
    @JoinTable(
            name = "vehicle_delivery_route",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "delivery_route_id")
    )
    private Set<DeliveryRoute> deliveryRoutes;

}
