package com.ems.employee_management_system.repo;

import com.ems.employee_management_system.pojo.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, String> {

    // Search by name (case-insensitive)
    List<Employee> findByEmployeeNameContainingIgnoreCase(String name);

    // Search by email
    Employee findByEmployeeEmail(String email);

    // Search by role
    List<Employee> findByEmployeeRole(String role);

    // Find active employees only
    List<Employee> findByIsActiveTrue();

    // Pagination support
    Page<Employee> findAll(Pageable pageable);

    // Custom search query (name, email, or role)
    @Query("{ $or: [ " +
            "{ 'employeeName': { $regex: ?0, $options: 'i' } }, " +
            "{ 'employeeEmail': { $regex: ?0, $options: 'i' } }, " +
            "{ 'employeeRole': { $regex: ?0, $options: 'i' } } " +
            "] }")
    List<Employee> searchEmployees(String keyword);
}