package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockItemRepo extends JpaRepository<StockItem,Long> {
}
