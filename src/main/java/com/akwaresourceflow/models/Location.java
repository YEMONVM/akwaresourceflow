package com.akwaresourceflow.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String longitude;
    private String latitude;
    private LocalTime timestamp;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tracking> trackings;

    // Convert latitude to double
    public double getLatitudeAsDouble() {
        return Double.parseDouble(latitude);
    }

    // Convert longitude to double
    public double getLongitudeAsDouble() {
        return Double.parseDouble(longitude);
    }

    @Override
    public String toString() {
        return "Lat: " + latitude + ", Lon: " + longitude;
    }
}
