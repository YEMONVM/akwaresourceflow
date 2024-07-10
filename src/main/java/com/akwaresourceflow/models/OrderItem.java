package com.akwaresourceflow.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productname;
    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;
}
