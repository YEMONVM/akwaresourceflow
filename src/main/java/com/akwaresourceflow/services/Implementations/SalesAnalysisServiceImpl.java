package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.Bill;
import com.akwaresourceflow.models.OrderItem;
import com.akwaresourceflow.models.ProductSalesData;
import com.akwaresourceflow.repositories.BillRepo;
import com.akwaresourceflow.repositories.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesAnalysisServiceImpl {
    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private BillRepo billRepo;

    public List<Bill> getBillsBetweenDates(Date startDate, Date endDate) {
        return billRepo.findBillsBetweenDates(startDate, endDate);
    }

    public Double getTotalRevenueBetweenDates(Date startDate, Date endDate) {
        return billRepo.findTotalRevenueBetweenDates(startDate, endDate);
    }

    public List<Object[]> getTopSellingProducts() {
        return orderItemRepo.findTopSellingProducts();
    }

    /*public List<ProductSalesData> getTopSellingProducts() {
        List<OrderItem> orderItems = orderItemRepo.findAll();

        return orderItems.stream()
                .collect(Collectors.groupingBy(OrderItem::getProductname, Collectors.summingInt(OrderItem::getQuantity)))
                .entrySet().stream()
                .map(entry -> new ProductSalesData(entry.getKey(), entry.getValue()))
                .sorted((p1, p2) -> p2.getTotalQuantity() - p1.getTotalQuantity())
                .collect(Collectors.toList());

    }*/
}
