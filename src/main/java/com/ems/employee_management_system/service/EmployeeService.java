package com.ems.employee_management_system.service;

import com.ems.employee_management_system.pojo.Employee;
import com.ems.employee_management_system.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepo.findByIsActiveTrue();
    }

    // Get employee by ID
    public Optional<Employee> getEmployeeById(String id) {
        return employeeRepo.findById(id);
    }

    // Create new employee with auto-generated ID
    public Employee createEmployee(Employee employee) {
        String empId = "EMP" + (1000 + new Random().nextInt(9000));
        employee.setId(empId);
        employee.setCreatedAt(LocalDateTime.now());
        employee.setActive(true);
        return employeeRepo.save(employee);
    }

    // Update existing employee
    public Employee updateEmployee(Employee employee) {
        employee.setUpdatedAt(LocalDateTime.now());
        return employeeRepo.save(employee);
    }

    // Soft delete (mark as inactive)
    public void softDeleteEmployee(String id) {
        Optional<Employee> emp = employeeRepo.findById(id);
        if (emp.isPresent()) {
            Employee employee = emp.get();
            employee.setActive(false);
            employeeRepo.save(employee);
        }
    }

    // Hard delete
    public void deleteEmployee(String id) {
        employeeRepo.deleteById(id);
    }

    // Delete all employees
    public void deleteAllEmployees() {
        employeeRepo.deleteAll();
    }

    // Search employees
    public List<Employee> searchEmployees(String keyword) {
        return employeeRepo.searchEmployees(keyword);
    }

    // Get paginated employees
    public Page<Employee> getEmployeesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return employeeRepo.findAll(pageable);
    }

    // Get employee count
    public long getEmployeeCount() {
        return employeeRepo.count();
    }

    // Calculate total salary
    public double getTotalSalary() {
        return getAllEmployees().stream()
                .mapToDouble(emp -> Double.parseDouble(emp.getEmployeeSalary()))
                .sum();
    }
}