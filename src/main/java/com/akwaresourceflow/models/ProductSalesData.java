package com.akwaresourceflow.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSalesData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantite_vendu")
    private int QuantiteVendu;

    @Column(name = "prix_achat")
    private float PrixAchat;

    @Column(name = "prix_vente")
    private float prixVente;

    @Column(name = "date_vente")
    private LocalDate dateVente;


    public ProductSalesData(String key, Integer value) {
    }

    @ManyToOne
    @JoinColumn(name = "stock_item_id")
    private StockItem stockItem;


}
