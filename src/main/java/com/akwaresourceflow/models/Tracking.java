package com.akwaresourceflow.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vehicleId; // Change this to Long
    private String locationData;
    private double longitude;
    private double latitude;
    private String timestamp;
    private double speed;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
