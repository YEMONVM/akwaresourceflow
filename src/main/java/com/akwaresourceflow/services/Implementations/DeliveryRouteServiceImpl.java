package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.DeliveryRoute;
import com.akwaresourceflow.repositories.AppUserRepo;
import com.akwaresourceflow.repositories.DeliveryRouteRepo;
import com.akwaresourceflow.services.Interfaces.DeliveryRouteService;
import com.google.maps.model.DistanceMatrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DeliveryRouteServiceImpl implements DeliveryRouteService {
    private final DeliveryRouteRepo deliveryRouteRepo;

    public DeliveryRouteServiceImpl(DeliveryRouteRepo deliveryRouteRepo) {
        this.deliveryRouteRepo = deliveryRouteRepo;
    }

    @Override
    public List<DeliveryRoute> getAllDeliveryRoutes() {
        return null;
    }

    @Override
    public DeliveryRoute getDeliveryRoute(Long id) {
        return null;
    }

    @Override
    public DeliveryRoute saveDeliveryRoute(DeliveryRoute deliveryRoute) {
        return null;
    }

    @Override
    public void deleteDeliveryRoute(Long id) {

    }

    @Override
    public DeliveryRoute getOptimalRoute(String origin, String destination, LocalTime timestamp) {
        return null;
    }
}
