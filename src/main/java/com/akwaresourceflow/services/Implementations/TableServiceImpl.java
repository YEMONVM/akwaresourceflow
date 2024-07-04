package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.DiningTable;
import com.akwaresourceflow.repositories.DiningTableRepo;
import com.akwaresourceflow.services.Interfaces.TableService;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TableServiceImpl implements TableService {

    @Autowired

    private DiningTableRepo diningTableRepo;

    @Override
    public List<DiningTable> getAllTables() {
        try {
            return diningTableRepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all tables", e);
        }
    }

    @Override
    public Optional<DiningTable> getTableById(Long id) {
        try {
            return diningTableRepo.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch table with id " + id, e);
        }
    }

    @Override
    public DiningTable saveTable(DiningTable diningTable) {
        try {
            return diningTableRepo.save(diningTable);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save table", e);
        }
    }

    @Override
    public DiningTable updateTable(Long id, DiningTable diningTableDetails) throws ResourceNotFoundException {
        try {
            return diningTableRepo.findById(id).map(diningTable -> {
                diningTable.setTablenumber(diningTableDetails.getTablenumber());
                diningTable.setCapacity(diningTableDetails.getCapacity());
                diningTable.setStatus(diningTableDetails.getStatus());
                return diningTableRepo.save(diningTable);
            }).orElseThrow(() -> new ResourceNotFoundException("Table not found with id " + id));
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update table with id " + id, e);
        }
    }

    @Override
    public void deleteTable(Long id) {
        try {
            diningTableRepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete table with id " + id, e);
        }
    }
}
