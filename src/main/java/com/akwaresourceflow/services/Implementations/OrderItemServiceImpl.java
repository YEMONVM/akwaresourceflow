package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.OrderItem;
import com.akwaresourceflow.repositories.OrderItemRepo;
import com.akwaresourceflow.services.Interfaces.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepo orderItemRepo;

    public OrderItemServiceImpl(OrderItemRepo orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepo.findAll();
    }

    @Override
    public OrderItem getOrderItem(Long id) {
        return orderItemRepo.findById(id).orElse(null);
    }

    @Override
    public OrderItem saveOrderItem(OrderItem orderItem) {
        return orderItemRepo.save(orderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepo.deleteById(id);
    }

    @Override
    public void updateOrderItem(Long id, OrderItem orderItem) {
        if (orderItemRepo.existsById(id)) {
            orderItem.setId(id);
            orderItemRepo.save(orderItem);
        }
    }
}
