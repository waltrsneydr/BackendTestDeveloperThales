package com.thaleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private int id;
    private String employee_name;
    private double employee_salary;
    private int employee_age;
    private String profile_image;
    private double employee_anual_salary;
}
