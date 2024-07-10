package com.akwaresourceflow.models;

import com.akwaresourceflow.enums.TrafficFlow;
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
public class DeliveryRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;
    private String destination;
    private LocalTime timestamp;
    private double fuelconsumption;
    private TrafficFlow trafficflow;

    @ManyToMany(mappedBy = "deliveryRoutes")
    private Set<Vehicle> vehicles;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "delivery_route_stock_item",
            joinColumns = @JoinColumn(name = "delivery_route_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_item_id")
    )
    private Set<StockItem> stockItems;

}
