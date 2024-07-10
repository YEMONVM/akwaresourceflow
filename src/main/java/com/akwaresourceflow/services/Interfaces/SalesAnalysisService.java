package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.ProductSalesData;

import java.util.Date;
import java.util.List;

public interface SalesAnalysisService {
    List<ProductSalesData> getTopSellingProducts();

    Double getTotalRevenueBetweenDates(Date startDate, Date endDate);
}
