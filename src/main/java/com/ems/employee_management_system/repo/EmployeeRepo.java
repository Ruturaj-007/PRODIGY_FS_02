package com.ems.employee_management_system.repo;

import com.ems.employee_management_system.pojo.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository // Marks this interface as a Repository Bean, so Spring can manage it and inject it wherever needed
public interface EmployeeRepo extends MongoRepository<Employee, String> {
    // This repo will manage Employee and EmployeeId as a String
}
