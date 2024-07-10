package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillRepo extends JpaRepository<Bill, Long> {
}
