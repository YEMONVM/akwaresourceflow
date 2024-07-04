package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.StockItem;

import java.util.List;

public interface StockItemService {
    List<StockItem> getAllStockItem();
    List<StockItem> getByName(String name);
    List<StockItem> getByCategory(String category);
    void deleteStockItem(Long idStockItem);
    void updateStockItem(Long idStockItem,StockItem stockItem);
}
