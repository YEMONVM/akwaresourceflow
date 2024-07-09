package com.akwaresourceflow.models;

import com.akwaresourceflow.enums.VehicleType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

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
    private String status; // Available, In Transit, Under Maintenance, etc.
}
