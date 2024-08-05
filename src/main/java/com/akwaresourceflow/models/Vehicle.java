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

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    private String currentLocation;
    private int capacity;
    private String destination;
    private double latitude;
    private double longitude;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    @ManyToMany
    @JoinTable(
            name = "vehicle_delivery_route",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "delivery_route_id")
    )
    private Set<DeliveryRoute> deliveryRoutes;

    public void setLocation(Location location) {
        this.latitude = Double.parseDouble(location.getLatitude());
        this.longitude = Double.parseDouble(location.getLongitude());
        this.currentLocation = location.toString(); // Assuming Location has a meaningful toString() method
    }
}
