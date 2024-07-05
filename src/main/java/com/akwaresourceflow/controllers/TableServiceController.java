package com.akwaresourceflow.controllers;

import com.akwaresourceflow.models.DiningTable;
import com.akwaresourceflow.services.Interfaces.TableService;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tables")
public class TableServiceController {

    @Autowired
    private TableService tableService;

    @GetMapping
    public List<DiningTable> getAllTables() {
        return tableService.getAllTables();
    }

    @GetMapping("/{id}")
    public DiningTable getTableById(@PathVariable Long id) {
        return tableService.getTableById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Table not found with id " + id));
    }

    @PostMapping
    public DiningTable createTable(@RequestBody DiningTable diningTable) {
        return tableService.saveTable(diningTable);
    }

    @PutMapping("/{id}")
    public DiningTable updateTable(@PathVariable Long id, @RequestBody DiningTable diningTableDetails) throws ResourceNotFoundException {
        return tableService.updateTable(id, diningTableDetails);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
    }
}
