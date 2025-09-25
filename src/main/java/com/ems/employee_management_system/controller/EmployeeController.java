package com.ems.employee_management_system.controller;

import org.springframework.ui.Model;
import com.ems.employee_management_system.pojo.ConfirmationForm;
import com.ems.employee_management_system.pojo.Employee;
import com.ems.employee_management_system.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.Random;


@Controller // listens to requests from the browser
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo; // DI

    // Home Page
    @GetMapping("/")
    public String getIndex(Model model) { // Use Model if you want to pass data to the view (like employee list or error messages).
        List<Employee> employeeList = employeeRepo.findAll(); // Fetches all employees
        model.addAttribute("employees", employeeList);
        model.addAttribute("employee", new Employee()); // New emp obj for form binding
        model.addAttribute("confirmationForm", new ConfirmationForm());
        return "index";
    }

    // Create new Employee
    @PostMapping("/create")
    public String newEmployee(@ModelAttribute Employee employee, Model model) {
        model.addAttribute("employee", new Employee());
        String empId = "EMP" + (1000 + new Random().nextInt(9000));
        employee.setId(empId);
        employeeRepo.save(employee);
        return "redirect:/"; // Redirects back to homepage
    }

    // Update Employee
    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee, Model model) {
        Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId()); // Takes employee data from form
        if(existingEmployee.isPresent()) {
            employeeRepo.save(employee);
        }
        else {
            model.addAttribute("errorMessage", "Employee with ID " + employee.getId() + " not found.");
        }
        return "redirect:/";
    }

    // Delete by ID
    @PostMapping("/remove")
    public String removeEmployee(@ModelAttribute Employee employee) { // Skip Model if you’re just doing DB operations and redirecting.
        employeeRepo.deleteById(employee.getId());
        return "redirect:/";
    }

    // Delete All Employees
    @PostMapping("/remove/all")
    public String removeAll(@ModelAttribute ConfirmationForm confirmationForm) {
        if ("Yes".equalsIgnoreCase(confirmationForm.getConfirmation())) {
            employeeRepo.deleteAll();
        }
        return "redirect:/";
    }



}
