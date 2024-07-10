package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.BillOrderItem;
import java.util.List;
import java.util.Optional;

public interface BillOrderItemService {
    BillOrderItem createBillOrderItem(BillOrderItem billOrderItem);
    Optional<BillOrderItem> getBillOrderItemById(Long id);
    List<BillOrderItem> getAllBillOrderItems();
    void deleteBillOrderItem(Long id);
}
