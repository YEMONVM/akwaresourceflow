package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.BillOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillOrderItemRepo extends JpaRepository<BillOrderItem, Long> {
}
