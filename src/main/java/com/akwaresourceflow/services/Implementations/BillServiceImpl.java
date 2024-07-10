package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.Bill;
import com.akwaresourceflow.models.BillOrderItem;
import com.akwaresourceflow.repositories.BillRepo;
import com.akwaresourceflow.repositories.BillOrderItemRepo;
import com.akwaresourceflow.repositories.RestaurantTableRepo;
import com.akwaresourceflow.services.Interfaces.BillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BillServiceImpl implements BillService {
    @Autowired
    private final BillRepo billRepo;

    @Autowired
    private final BillOrderItemRepo billOrderItemRepo;

    @Autowired
    private final RestaurantTableRepo restaurantTableRepo;

    public BillServiceImpl(BillRepo billRepo, BillOrderItemRepo billOrderItemRepo, RestaurantTableRepo restaurantTableRepo) {
        this.billRepo = billRepo;
        this.billOrderItemRepo = billOrderItemRepo;
        this.restaurantTableRepo = restaurantTableRepo;
    }

    @Override
    public Bill createBill(List<BillOrderItem> billOrderItems, Long diningTableId) {
        Bill bill = new Bill();
        bill.setOrderDate(new Date());
        bill.setBillOrderItems(billOrderItems);
        bill.setRestaurantTable(restaurantTableRepo.findById(diningTableId).orElseThrow(() -> new RuntimeException("Dining table not found")));
        bill.setTotalAmount(calculateTotal(billOrderItems));
        return billRepo.save(bill);
    }

    @Override
    public Bill getBillById(Long billId) {
        Optional<Bill> billOptional = billRepo.findById(billId);
        return billOptional.orElseThrow(() -> new RuntimeException("Bill not found with id: " + billId));
    }

    @Override
    public Bill updateBill(Long billId, Bill bill) {
        Optional<Bill> billOptional = billRepo.findById(billId);
        if (billOptional.isPresent()) {
            Bill existingBill = billOptional.get();
            existingBill.setOrderDate(bill.getOrderDate());
            existingBill.setBillOrderItems(bill.getBillOrderItems());
            existingBill.setRestaurantTable(bill.getRestaurantTable());
            existingBill.setTotalAmount(calculateTotal(bill.getBillOrderItems()));
            return billRepo.save(existingBill);
        } else {
            throw new RuntimeException("Bill not found with id: " + billId);
        }
    }

    @Override
    public void deleteBill(Long billId) {
        Optional<Bill> billOptional = billRepo.findById(billId);
        if (billOptional.isPresent()) {
            billRepo.delete(billOptional.get());
        } else {
            throw new RuntimeException("Bill not found with id: " + billId);
        }
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepo.findAll();
    }

    @Override
    public double calculateTotal(List<BillOrderItem> billOrderItems) {
        return billOrderItems.stream()
                .mapToDouble(item -> item.getOrderItem().getPrice() * item.getQuantity())
                .sum();
    }
}
