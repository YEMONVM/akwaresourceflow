package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.DeliveryRoute;

import java.time.LocalTime;
import java.util.List;

public interface DeliveryRouteService {
    List<DeliveryRoute> getAllDeliveryRoutes();
    DeliveryRoute getDeliveryRoute(Long id);
    DeliveryRoute saveDeliveryRoute(DeliveryRoute deliveryRoute);
    void deleteDeliveryRoute(Long id);
    DeliveryRoute getOptimalRoute(String origin, String destination, LocalTime timestamp);
}
