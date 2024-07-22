package com.akwaresourceflow.services.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomTomConfig {
    @Value("${tomtom.api.key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}

