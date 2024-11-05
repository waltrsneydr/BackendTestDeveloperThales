package com.thaleservice.service;

import com.thaleservice.dto.EmployeesResponseDto;
import com.thaleservice.dto.OneEmployeesResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class ThaleTestDeveloperServiceImpl implements  IThaleTestDeveloperService{
    private final RestTemplate restTemplate;
    private String url;
    private static final String BASE_URL = "https://dummy.restapiexample.com/api/v1/";
    private static final String COOKIE_VALUE = "humans_21909=1";
    private static final String ACCEPT_HEADER = "application/json";
    private String responseError;

    @Autowired
    public ThaleTestDeveloperServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public ResponseEntity<EmployeesResponseDto> getAllEmployees() {

        url = BASE_URL + "employees";
        try {
                ResponseEntity<EmployeesResponseDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, createHttpEntity(), EmployeesResponseDto.class);
            if(responseEntity.getBody().getData() != null){
                responseEntity.getBody().getData().forEach( employee -> {double annualSalary =  employee.getEmployee_salary() * 12;
                    employee.setEmployee_anual_salary(annualSalary);});
            }else{
                return  ResponseEntity.ok(EmployeesResponseDto.builder().status("ok").message("No records found").build());
            }
            return responseEntity;
        } catch (HttpClientErrorException e) {
            errorResponseMethod(e);
            return ResponseEntity.ok(EmployeesResponseDto.builder().status("failed").message(responseError).build());
        }

    }

    @Override
    public ResponseEntity<OneEmployeesResponseDto> getEmployeeById(String idEmployee) {

        url = BASE_URL + "employee/" + idEmployee;
        try {
            ResponseEntity<OneEmployeesResponseDto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, createHttpEntity(), OneEmployeesResponseDto.class);
            if(responseEntity.getBody().getData() != null){
                responseEntity.getBody().getData().setEmployee_anual_salary(responseEntity.getBody().getData().getEmployee_salary()*12);
            }else{
                return  ResponseEntity.ok(OneEmployeesResponseDto.builder().status("ok").message("No records found").build());
            }
            return responseEntity;

        }catch (HttpClientErrorException e) {
            errorResponseMethod(e);
            return ResponseEntity.ok(OneEmployeesResponseDto.builder().status("failed").message(responseError).build());
        }
    }

    private HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", COOKIE_VALUE);
        headers.set("Accept", ACCEPT_HEADER);
        return new HttpEntity<>(headers);
    }

    private String errorResponseMethod(HttpClientErrorException e){
        responseError = "Error: "+e.getStatusCode();
        if(e.getStatusCode().equals(HttpStatus.TOO_MANY_REQUESTS)){
            responseError = "Intente mas tarde";
        }
        return responseError;
    }
}
