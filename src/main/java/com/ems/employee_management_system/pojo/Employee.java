package com.ems.employee_management_system.pojo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employee")
public class Employee {

    @Id
    private String id;

    @NotBlank(message = "Employee name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String employeeName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    @Indexed(unique = true) // Ensure unique emails
    private String employeeEmail;

    @NotNull(message = "Phone number is required")
    @Min(value = 1000000000L, message = "Phone number must be 10 digits")
    @Max(value = 9999999999L, message = "Phone number must be 10 digits")
    private Long employeePhone;

    @NotBlank(message = "Gender is required")
    private String employeeGender;

    @NotBlank(message = "Salary is required")
    @Pattern(regexp = "^[0-9]+$", message = "Salary must be a number")
    private String employeeSalary;

    @NotBlank(message = "Role is required")
    private String employeeRole;

    private String department; // New field
    private LocalDateTime createdAt; // Track creation time
    private LocalDateTime updatedAt; // Track update time
    private boolean isActive = true; // Soft delete flag
}