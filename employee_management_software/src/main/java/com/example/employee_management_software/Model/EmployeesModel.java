package com.example.employee_management_software.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesModel {

    @NotEmpty(message = "Id must not be empty")
    @Size(min = 3, max = 8,message = "ID must be 3 numbers at Least")
    private String id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 5, max = 12,message = "Name Must be at least 5 and max 12 letter")
    private String name;

    @Positive
    @NotNull(message = "Age must not be empty")
    @Min(value = 26, message = "Age must be at least 26")
    @Max(value = 60, message = "Age must be less or equal than 60")
    private int age;

    @Pattern(regexp = "supervisor|coordinator",message = "position must be supervisor or coordinator")
    @NotEmpty
    private String position;

    @AssertFalse(message = "must be false")
    private boolean onLeave;

    @NotNull(message = "employment Year must not be null")
    @Min(value = 1960,message = "must be between 1960 and 2023")
    @Max(value = 2023, message = "must be between 1960 and 2023")
    private int employmentYear;

    @Positive
    @Min(value = 15, message = "Annual leave between 15 and 32")
    @Max(value = 32, message = "Annual leave between 15 and 32")
    private int annualLeave;



}
