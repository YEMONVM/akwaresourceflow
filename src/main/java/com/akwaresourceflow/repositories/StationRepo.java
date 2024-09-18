package com.akwaresourceflow.repositories;

import com.akwaresourceflow.dto.StationDTO;
import com.akwaresourceflow.models.Station;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StationRepo extends JpaRepository<Station, Long> {
    // Remove the custom query method as we'll use the built-in pagination
}
