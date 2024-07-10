package com.akwaresourceflow.models;

import com.akwaresourceflow.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Date;

@Configuration
public class DatabaseLoader {
    /*
    @Bean
    CommandLineRunner initDatabase(StationRepo stationRepository,
                                   StockItemRepo stockItemRepository,
                                   RestaurantRepo restaurantRepository,
                                   ScheduleRepo scheduleRepository,
                                   OrderItemRepo orderItemRepository,
                                   BillRepo billRepository) {
        return args -> {
            // Insertion des Stations
            Station station1 = new Station(null, "Station 1", "Address 1", "City 1", "123456789");
            Station station2 = new Station(null, "Station 2", "Address 2", "City 2", "987654321");
            stationRepository.saveAll(Arrays.asList(station1, station2));

            // Insertion des StockItems
            StockItem stockItem1 = new StockItem(null, "Product 1", 100, 10, "Category 1");
            StockItem stockItem2 = new StockItem(null, "Product 2", 200, 20, "Category 2");
            stockItemRepository.saveAll(Arrays.asList(stockItem1, stockItem2));

            // Insertion des Restaurants
            Restaurant restaurant1 = new Restaurant(null, "Restaurant 1", "123456789");
            Restaurant restaurant2 = new Restaurant(null, "Restaurant 2", "987654321");
            restaurantRepository.saveAll(Arrays.asList(restaurant1, restaurant2));

            // Insertion des Schedules
            Schedule schedule1 = new Schedule(null, new Date(), new Date(), null);
            Schedule schedule2 = new Schedule(null, new Date(), new Date(), null);
            scheduleRepository.saveAll(Arrays.asList(schedule1, schedule2));

            // Insertion des OrderItems
            OrderItem orderItem1 = new OrderItem(null, "Product 1", 10, 100.0);
            OrderItem orderItem2 = new OrderItem(null, "Product 2", 5, 50.0);
            orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2));

            // Insertion des Bills
            Bill bill1 = new Bill(null, new Date(), 150.0, Arrays.asList(orderItem1));
            Bill bill2 = new Bill(null, new Date(), 75.0, Arrays.asList(orderItem2));
            billRepository.saveAll(Arrays.asList(bill1, bill2));

            // Récupération et affichage des données pour vérification
            System.out.println("Stations: " + stationRepository.findAll());
            System.out.println("Stock Items: " + stockItemRepository.findAll());
            System.out.println("Restaurants: " + restaurantRepository.findAll());
            System.out.println("Schedules: " + scheduleRepository.findAll());
            System.out.println("Order Items: " + orderItemRepository.findAll());
            System.out.println("Bills: " + billRepository.findAll());


        };

    }

     */

}
