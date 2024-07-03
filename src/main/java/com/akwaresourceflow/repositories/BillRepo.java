package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepo extends JpaRepository<Bill,Long> {
}
