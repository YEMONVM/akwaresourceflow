package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.StockItem;
import com.akwaresourceflow.repositories.StockItemRepo;
import com.akwaresourceflow.services.Interfaces.StockItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StockItemServiceImpl implements StockItemService {
    private final StockItemRepo stockItemRepo;

    @Autowired
    public StockItemServiceImpl(StockItemRepo stockItemRepo) {
        this.stockItemRepo = stockItemRepo;
    }

    @Override
    public List<StockItem> getAllStockItem() {
        return stockItemRepo.findAll();
    }

    @Override
    public List<StockItem> getByName(String name) {
        return stockItemRepo.findByName(name);
    }

    @Override
    public List<StockItem> getByCategory(String category) {
        return stockItemRepo.findByCategory(category);
    }

    @Override
    public void deleteStockItem(Long idStockItem) {
        Optional<StockItem> stockItemOptional = stockItemRepo.findById(idStockItem);
        if (stockItemOptional.isPresent()) {
            stockItemRepo.delete(stockItemOptional.get());
        } else {
            throw new RuntimeException("StockItem not found with id: " + idStockItem);
        }
    }

    @Override
    public void updateStockItem(Long idStockItem, StockItem stockItem) {
        Optional<StockItem> stockItemOptional = stockItemRepo.findById(idStockItem);
        if (stockItemOptional.isPresent()) {
            StockItem existingStockItem = stockItemOptional.get();
            existingStockItem.setName(stockItem.getName());
            existingStockItem.setQuantity(stockItem.getQuantity());
            existingStockItem.setThreshold(stockItem.getThreshold());
            stockItemRepo.save(existingStockItem);
        } else {
            throw new RuntimeException("StockItem not found with id: " + idStockItem);
        }

    }
}
