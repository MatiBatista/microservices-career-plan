package com.eldar.sales_service.rest_service;

import com.eldar.sales_service.rest_service.dtos.responses.CustomerResponseDTO;
import com.eldar.sales_service.rest_service.dtos.responses.EmployeeResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "person-service")
public interface PersonClient {

    @GetMapping("/customers/{id}")
    CustomerResponseDTO getCustomerById(@PathVariable("id") Long id,
                                @RequestHeader("Authorization") String authHeader);

    @GetMapping("/employees/{id}")
    EmployeeResponseDTO getEmployeeById(@PathVariable("id") Long id,
                                        @RequestHeader("Authorization") String authHeader);



}
