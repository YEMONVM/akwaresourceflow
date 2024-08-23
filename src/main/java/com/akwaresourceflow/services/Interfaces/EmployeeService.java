package com.akwaresourceflow.services.Interfaces;

import com.akwaresourceflow.models.Employee;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);

    Employee saveEmployee(Employee employee);


    Employee updateEmployee(Long id, Employee employeeDetails) throws ResponseStatusException;
    void deleteEmployee(Long id);
}
