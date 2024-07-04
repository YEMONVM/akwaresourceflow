package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.Bill;
import com.akwaresourceflow.models.OrderItem;
import com.akwaresourceflow.repositories.BillRepo;
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

    public BillServiceImpl(BillRepo billRepo) {
        this.billRepo = billRepo;
    }

    @Override
    public Bill createBill(OrderItem orderItem) {
        Bill bill = new Bill();
        bill.setOrder(orderItem);
        bill.setOrderdate(new Date());
        bill.setTotalamount(calculateTotal(bill));
        return billRepo.save(bill);
    }

    @Override
    public Bill getBillById(Long billId) {
        Optional<Bill> billOptional = billRepo.findById(billId);
        return billOptional.orElseThrow(() -> new RuntimeException("Bill not found with id: " + billId));
    }

    @Override
    public void updateBill(Long billId, Bill bill) {
        Optional<Bill> billOptional = billRepo.findById(billId);
        if (billOptional.isPresent()) {
            Bill existingBill = billOptional.get();
            existingBill.setOrderdate(bill.getOrderdate());
            existingBill.setTotalamount(calculateTotal(bill));
            billRepo.save(existingBill);
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
    public double calculateTotal(Bill bill) {
        return 0;
    }
}
