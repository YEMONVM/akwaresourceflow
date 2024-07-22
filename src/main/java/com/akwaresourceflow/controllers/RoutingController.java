package com.akwaresourceflow.controllers;

import com.akwaresourceflow.services.Interfaces.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/routing")
public class RoutingController {

    @Autowired
    private RoutingService routingService;

    @GetMapping("/optimize")
    public String getOptimizedRoute(@RequestParam List<String> locations) {
        return routingService.getOptimizedRoute(locations);
    }

    @PostMapping("/optimize")
    public ResponseEntity<String> optimizeRoute(@RequestBody List<String> locations) {
        String optimizedRoute = routingService.getOptimizedRoute(locations);
        if (optimizedRoute != null) {
            return ResponseEntity.ok(optimizedRoute);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in route optimization");
        }
    }
}
