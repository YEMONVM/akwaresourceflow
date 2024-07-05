package com.akwaresourceflow.controllers;

import com.akwaresourceflow.models.Bill;
import com.akwaresourceflow.models.OrderItem;
import com.akwaresourceflow.services.Interfaces.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private BillService billService;
    @PostMapping
    public Bill createBill(@RequestBody OrderItem orderItem) {
        return billService.createBill(orderItem);
    }

    @GetMapping("/{id}")
    public Bill getBillById(@PathVariable Long id) {
        return billService.getBillById(id);
    }

    @PutMapping("/{id}")
    public Bill updateBill(@PathVariable Long id, @RequestBody Bill bill) {
        billService.updateBill(id, bill);
        return billService.getBillById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
    }

    @GetMapping
    public List<Bill> getAllBills() {
        return billService.getAllBills();
    }
}
