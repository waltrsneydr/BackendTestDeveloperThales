package com.thaleservice.service;

import com.thaleservice.dto.EmployeesResponseDto;
import com.thaleservice.dto.OneEmployeesResponseDto;
import org.springframework.http.ResponseEntity;

public interface IThaleTestDeveloperService {

    ResponseEntity<OneEmployeesResponseDto> getEmployeeById(String idEmployee);
    ResponseEntity<EmployeesResponseDto> getAllEmployees();

}
