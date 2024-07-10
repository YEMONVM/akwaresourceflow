package com.akwaresourceflow.controllers;

import com.akwaresourceflow.models.ProductSalesData;
import com.akwaresourceflow.services.Interfaces.SalesAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/reports")
public class SalesAnalysisController {


    private SalesAnalysisService salesAnalysisService;

    @GetMapping("/sales")
    public List<ProductSalesData> getTopSellingProducts() {
        return salesAnalysisService.getTopSellingProducts();
    }

    @GetMapping("/revenue")
    public Double getTotalRevenue(@RequestParam Date startDate, @RequestParam Date endDate) {
        return salesAnalysisService.getTotalRevenueBetweenDates(startDate, endDate);
    }
}
