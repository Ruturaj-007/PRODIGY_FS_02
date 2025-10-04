package com.ems.employee_management_system.controller;

import com.ems.employee_management_system.pojo.ConfirmationForm;
import com.ems.employee_management_system.pojo.Employee;
import com.ems.employee_management_system.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Home Page with Dashboard
    @GetMapping("/")
    public String getIndex(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            Model model,
            Authentication authentication
    ) {
        List<Employee> employees;

        // Search functionality
        if (search != null && !search.isEmpty()) {
            employees = employeeService.searchEmployees(search);
        } else {
            employees = employeeService.getAllEmployees();
        }

        // Dashboard statistics
        model.addAttribute("totalEmployees", employeeService.getEmployeeCount());
        model.addAttribute("totalSalary", employeeService.getTotalSalary());
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        model.addAttribute("confirmationForm", new ConfirmationForm());
        model.addAttribute("currentUser", authentication != null ? authentication.getName() : "Guest");
        model.addAttribute("searchQuery", search);

        return "index";
    }

    // Create new Employee with validation
    @PostMapping("/create")
    public String createEmployee(
            @Valid @ModelAttribute Employee employee,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Validation failed. Please check your input.");
            return "redirect:/";
        }

        try {
            employeeService.createEmployee(employee);
            redirectAttributes.addFlashAttribute("success", "Employee created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to create employee: " + e.getMessage());
        }

        return "redirect:/";
    }

    // Show Update Form
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);

        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "update";
        } else {
            redirectAttributes.addFlashAttribute("error", "Employee not found!");
            return "redirect:/";
        }
    }

    // Update Employee
    @PostMapping("/update")
    public String updateEmployee(
            @Valid @ModelAttribute Employee employee,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Validation failed. Please check your input.");
            return "redirect:/";
        }

        try {
            employeeService.updateEmployee(employee);
            redirectAttributes.addFlashAttribute("success", "Employee updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to update employee: " + e.getMessage());
        }

        return "redirect:/";
    }

    // Delete Employee by ID
    @PostMapping("/remove")
    public String removeEmployee(@RequestParam String id, RedirectAttributes redirectAttributes) {
        try {
            employeeService.deleteEmployee(id);
            redirectAttributes.addFlashAttribute("success", "Employee deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete employee: " + e.getMessage());
        }
        return "redirect:/";
    }

    // Delete All Employees
    @PostMapping("/remove/all")
    public String removeAll(
            @ModelAttribute ConfirmationForm confirmationForm,
            RedirectAttributes redirectAttributes
    ) {
        if ("Yes".equalsIgnoreCase(confirmationForm.getConfirmation())) {
            employeeService.deleteAllEmployees();
            redirectAttributes.addFlashAttribute("success", "All employees deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Confirmation failed. Please type 'Yes' to delete all.");
        }
        return "redirect:/";
    }

    // Search Employees
    @GetMapping("/search")
    public String searchEmployees(@RequestParam String keyword, Model model) {
        List<Employee> employees = employeeService.searchEmployees(keyword);
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        model.addAttribute("confirmationForm", new ConfirmationForm());
        model.addAttribute("searchQuery", keyword);
        return "index";
    }
}