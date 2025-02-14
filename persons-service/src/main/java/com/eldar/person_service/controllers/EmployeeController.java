package com.eldar.person_service.controllers;

import com.eldar.person_service.dtos.request.CustomerRequestDTO;
import com.eldar.person_service.dtos.request.EmployeeRequestDTO;
import com.eldar.person_service.dtos.response.CustomerResponseDTO;
import com.eldar.person_service.dtos.response.EmployeeResponseDTO;
import com.eldar.person_service.models.Employee;
import com.eldar.person_service.services.contracts.CustomerService;
import com.eldar.person_service.services.contracts.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping()
    @Operation(summary = "Get all employes", description = "Retrieve all employes")
    public ResponseEntity<List<EmployeeResponseDTO>> getAll(){
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by id", description = "Retrieve a employee by its id")
    public ResponseEntity<EmployeeResponseDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Add employee", description = "Add a new employee")
    public ResponseEntity<Void> add(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO){
        employeeService.add(employeeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update employee", description = "Update an existing employee")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody EmployeeRequestDTO employeeRequestDTO){
        employeeService.update(id,employeeRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee", description = "Delete a employee by its id")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }

}
