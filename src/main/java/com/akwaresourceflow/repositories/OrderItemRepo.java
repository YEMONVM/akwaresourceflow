package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

    @Query("SELECT o.productName, SUM(o.quantity) as totalQuantity FROM OrderItem o GROUP BY o.productName ORDER BY totalQuantity DESC")
    List<Object[]> findTopSellingProducts();
}
