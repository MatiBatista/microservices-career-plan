package com.eldar.person_service.services.contracts;

import com.eldar.person_service.dtos.request.CustomerRequestDTO;
import com.eldar.person_service.dtos.request.EmployeeRequestDTO;
import com.eldar.person_service.dtos.response.CustomerResponseDTO;
import com.eldar.person_service.dtos.response.EmployeeResponseDTO;
import com.eldar.person_service.models.Employee;
import jakarta.validation.Valid;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponseDTO> getAll();

    EmployeeResponseDTO getById(Long id);

    void add(@Valid EmployeeRequestDTO employeeRequestDTO);

    void update(Long id, EmployeeRequestDTO  employeeRequestDTO);

    void delete(Long id);
}
