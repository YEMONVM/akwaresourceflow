package com.akwaresourceflow.controllers;

import com.akwaresourceflow.models.StockItem;
import com.akwaresourceflow.services.Interfaces.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/stockItems")
public class StockItemController {
    @Autowired
    private StockItemService stockItemService;

    @GetMapping
    public List<StockItem> getAllStockItems() {
        return stockItemService.getAllStockItem();
    }

    @GetMapping("/{id}")
    public StockItem getStockItem(@PathVariable Long id) {
        return stockItemService.getStockItem(id);
    }
    @GetMapping("/name/{name}")
    public List<StockItem> getStockItemsByName(@PathVariable String name) {
        return stockItemService.getByName(name);
    }

    @GetMapping("/category/{category}")
    public List<StockItem> getStockItemsByCategory(@PathVariable String category) {
        return stockItemService.getByCategory(category);
    }

    @PostMapping
    public StockItem createStockItem(@RequestBody StockItem stockItem) {
        return stockItemService.saveStockItem(stockItem);
    }

    @PutMapping("/{id}")
    public StockItem updateStockItem(@PathVariable Long id, @RequestBody StockItem stockItem) {
        stockItemService.updateStockItem(id, stockItem);
        return stockItem;
    }

    @DeleteMapping("/{id}")
    public void deleteStockItem(@PathVariable Long id) {
        stockItemService.deleteStockItem(id);
    }

}
