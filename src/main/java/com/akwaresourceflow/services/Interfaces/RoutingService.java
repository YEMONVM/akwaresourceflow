package com.akwaresourceflow.services.Interfaces;

import java.util.List;

public interface RoutingService {
    String getOptimizedRoute(List<String> locations);
    String buildUrl(List<String> locations);
    String sendGetRequest(String url);
}
