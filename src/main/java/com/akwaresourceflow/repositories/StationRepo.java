package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepo extends JpaRepository<Station,Long> {
}
