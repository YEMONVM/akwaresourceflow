package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.services.Interfaces.RoutingService;
import com.akwaresourceflow.services.config.TomTomConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class RoutingServiceImpl implements RoutingService {

    @Autowired
    private TomTomConfig tomTomConfig;

    private final String BASE_URL = "https://api.tomtom.com/routing/1/calculateRoute/";

    @Override
    public String getOptimizedRoute(List<String> locations) {
        String url = buildUrl(locations);
        return sendGetRequest(url);
    }

    @Override
    public String buildUrl(List<String> locations) {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        for (String location : locations) {
            urlBuilder.append(location).append(":");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1); // Remove the last colon
        urlBuilder.append("/json?key=").append(tomTomConfig.getApiKey());
        return urlBuilder.toString();
    }

    @Override
    public String sendGetRequest(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
