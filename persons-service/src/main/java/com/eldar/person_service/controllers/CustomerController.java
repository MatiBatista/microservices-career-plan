package com.eldar.person_service.controllers;


import com.eldar.person_service.dtos.request.CustomerRequestDTO;
import com.eldar.person_service.dtos.response.CustomerResponseDTO;
import com.eldar.person_service.services.contracts.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping()
    @Operation(summary = "Get all customers", description = "Retrieve all customers")
    public ResponseEntity<List<CustomerResponseDTO>> getAll(){
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by id", description = "Retrieve a customer by its id")
    public ResponseEntity<CustomerResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Add customer", description = "Add a new customer")
    public ResponseEntity<Void> add(@Valid @RequestBody CustomerRequestDTO customerRequestDTO){
        customerService.add(customerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update customer", description = "Update an existing customer")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CustomerRequestDTO customerRequestDTO){
        customerService.update(id,customerRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete customer", description = "Delete a customer by its id")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        customerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
