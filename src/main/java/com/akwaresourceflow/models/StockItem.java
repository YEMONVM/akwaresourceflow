package com.akwaresourceflow.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
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
    private String category;

    @ManyToMany(mappedBy = "stockItems")
    private Set<DeliveryRoute> deliveryRoutes;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    @OneToMany(mappedBy = "stockItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductSalesData> productSalesData;

    @Column(name = "date_stock")
    private LocalDate dateStock;
}
