package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.Bill;
import com.akwaresourceflow.models.OrderItem;

import java.util.List;

public interface BillService {
    Bill createBill(OrderItem orderItem);
    Bill getBillById(Long billId);
    void updateBill(Long billId, Bill bill);
    void deleteBill(Long billId);
    List<Bill> getAllBills();
    double calculateTotal(Bill bill);
}
