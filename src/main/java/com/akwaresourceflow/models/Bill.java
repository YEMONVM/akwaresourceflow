package com.akwaresourceflow.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date orderDate;
    private double totalAmount;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillOrderItem> billOrderItems;

    @ManyToOne
    @JoinColumn(name = "restaurant_table_id")
    @JsonBackReference
    private RestaurantTable restaurantTable;
}
