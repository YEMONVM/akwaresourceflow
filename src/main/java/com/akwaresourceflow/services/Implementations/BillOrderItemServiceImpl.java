package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.BillOrderItem;
import com.akwaresourceflow.repositories.BillOrderItemRepo;
import com.akwaresourceflow.services.Interfaces.BillOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillOrderItemServiceImpl implements BillOrderItemService {

    @Autowired
    private BillOrderItemRepo billOrderItemRepo;

    @Override
    public BillOrderItem createBillOrderItem(BillOrderItem billOrderItem) {
        return billOrderItemRepo.save(billOrderItem);
    }

    @Override
    public Optional<BillOrderItem> getBillOrderItemById(Long id) {
        return billOrderItemRepo.findById(id);
    }

    @Override
    public List<BillOrderItem> getAllBillOrderItems() {
        return billOrderItemRepo.findAll();
    }

    @Override
    public void deleteBillOrderItem(Long id) {
        BillOrderItem billOrderItem = billOrderItemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill Order Item not found with id: " + id));
        billOrderItemRepo.delete(billOrderItem);
    }
}
