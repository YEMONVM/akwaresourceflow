package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.DiningTable;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TableService {
    List<DiningTable> getAllTables();
    Optional<DiningTable> getTableById(Long id);
    DiningTable saveTable(DiningTable diningTable);
    DiningTable updateTable(Long id, DiningTable diningTableDetails) throws ResourceNotFoundException;
    void deleteTable(Long id);
}
