package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiningTableRepo extends JpaRepository<DiningTable,Long> {
}
