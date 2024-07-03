package com.akwaresourceflow.repositories;

import com.akwaresourceflow.models.DeliveryRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRouteRepo extends JpaRepository<DeliveryRoute,Long> {
}
