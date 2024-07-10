package com.akwaresourceflow.controllers;

import com.akwaresourceflow.models.Bill;
import com.akwaresourceflow.models.BillOrderItem;
import com.akwaresourceflow.services.Interfaces.BillService;
import com.akwaresourceflow.services.Interfaces.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private RestaurantTableService restaurantTableService;

    @PostMapping("/billOrderItems")
    public ResponseEntity<Bill> createBill(@RequestBody List<BillOrderItem> billOrderItems, @RequestParam Long restaurantTableId) {
        Bill bill = billService.createBill(billOrderItems, restaurantTableId);
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        Bill bill = billService.getBillById(id);
        return ResponseEntity.ok(bill);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill billDetails) {
        billService.updateBill(id, billDetails);
        return ResponseEntity.ok(billDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        List<Bill> bills = billService.getAllBills();
        return ResponseEntity.ok(bills);
    }
}
