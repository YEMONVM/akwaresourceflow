package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.Bill;
import com.akwaresourceflow.models.BillOrderItem;

import java.util.List;

public interface BillService {
    Bill createBill(List<BillOrderItem> billOrderItems, Long diningTableId);
    Bill getBillById(Long billId);
    Bill updateBill(Long billId, Bill bill);
    void deleteBill(Long billId);
    List<Bill> getAllBills();
    double calculateTotal(List<BillOrderItem> billOrderItems);
}
