package com.thaleservice.controller;

import com.thaleservice.dto.EmployeesResponseDto;
import com.thaleservice.dto.OneEmployeesResponseDto;
import com.thaleservice.service.IThaleTestDeveloperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/employee")
public class thaleServiceController {

    private final IThaleTestDeveloperService thaleTestDeveloperService;

    public thaleServiceController(IThaleTestDeveloperService thaleTestDeveloperService) {
        this.thaleTestDeveloperService = thaleTestDeveloperService;
    }
    @GetMapping
    public ResponseEntity<EmployeesResponseDto> getAll() {
        return thaleTestDeveloperService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OneEmployeesResponseDto> getById(@PathVariable String id) {
        return thaleTestDeveloperService.getEmployeeById(id);
    }
}
