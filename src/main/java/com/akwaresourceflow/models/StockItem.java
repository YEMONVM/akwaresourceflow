package com.akwaresourceflow.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class StockItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantity;
    private int threshold;
    private String category;

    @ManyToMany(mappedBy = "stockItems")
    private Set<DeliveryRoute> deliveryRoutes;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;
}
