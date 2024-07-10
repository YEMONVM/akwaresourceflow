package com.akwaresourceflow.controllers;

import com.akwaresourceflow.models.BillOrderItem;
import com.akwaresourceflow.services.Interfaces.BillOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/billOrderItems")
public class BillOrderItemController {

    @Autowired
    private BillOrderItemService billOrderItemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BillOrderItem createBillOrderItem(@RequestBody BillOrderItem billOrderItem) {
        return billOrderItemService.createBillOrderItem(billOrderItem);
    }

    @GetMapping("/{id}")
    public BillOrderItem getBillOrderItemById(@PathVariable Long id) {
        return billOrderItemService.getBillOrderItemById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bill Order Item not found with id " + id));
    }

    @GetMapping
    public List<BillOrderItem> getAllBillOrderItems() {
        return billOrderItemService.getAllBillOrderItems();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBillOrderItem(@PathVariable Long id) {
        billOrderItemService.deleteBillOrderItem(id);
    }
}
