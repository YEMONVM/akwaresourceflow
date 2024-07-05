package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> getAllOrderItems();
    OrderItem getOrderItem(Long id);
    OrderItem saveOrderItem(OrderItem orderItem);
    void deleteOrderItem(Long id);
    void updateOrderItem(Long id, OrderItem orderItem);
}
