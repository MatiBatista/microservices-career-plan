package com.eldar.sales_service.rest_service;

import com.eldar.sales_service.rest_service.dtos.requests.ProductRequestDTO;
import com.eldar.sales_service.rest_service.dtos.responses.EmployeeResponseDTO;
import com.eldar.sales_service.rest_service.dtos.responses.ProductResponseDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "products-service", path = "/products")
public interface ProductClient {

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ProductResponseDTO getById(@PathVariable("id") Long id,
                           @RequestHeader("Authorization") String authHeader);

    @PutMapping("/sell/{id}")
    Void sell(@PathVariable Long id, @RequestParam int quantity, @RequestHeader("Authorization") String authHeader);


    @PutMapping("/revertStock/{id}")
    Void revertStock(@PathVariable Long id,  @RequestParam int quantity, @RequestHeader("Authorization") String authHeader);

}
