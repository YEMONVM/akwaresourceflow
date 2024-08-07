package com.akwaresourceflow.services.Implementations;

import com.akwaresourceflow.models.Employee;
import com.akwaresourceflow.repositories.EmployeeRepo;
import com.akwaresourceflow.services.Interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employeeDetails) throws ResponseStatusException {
        return employeeRepo.findById(id).map(employee -> {
            employee.setFirstname(employeeDetails.getFirstname());
            employee.setLastname(employeeDetails.getLastname());
            employee.setPosition(employeeDetails.getPosition());
            employee.setEmail(employeeDetails.getEmail()); // Updated
            employee.setPhone(employeeDetails.getPhone()); // Updated
            employee.setHireDate(employeeDetails.getHireDate()); // Updated
            employee.setSchedules(employeeDetails.getSchedules());
            return employeeRepo.save(employee);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id " + id));
    }

    @Override
    public void deleteEmployee(Long id) {
        try {
            employeeRepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete employee with id " + id, e);
        }
    }
}
