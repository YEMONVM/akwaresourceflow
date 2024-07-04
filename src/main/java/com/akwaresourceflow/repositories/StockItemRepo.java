package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockItemRepo extends JpaRepository<StockItem,Long> {
    List<StockItem> findByName(String name);
    List<StockItem> findByCategory(String category);
}
