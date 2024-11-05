package com.thaleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OneEmployeesResponseDto {
    private String status;
    private String message;
    private EmployeeDto data;

}
