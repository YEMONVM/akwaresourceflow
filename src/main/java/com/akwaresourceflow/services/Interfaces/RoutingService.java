package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.dto.LocationDTO;
import java.util.List;

public interface RoutingService {
    String getOptimizedRoute(List<LocationDTO> locations);
    String buildUrl(List<LocationDTO> locations);
    String sendGetRequest(String url);
}
