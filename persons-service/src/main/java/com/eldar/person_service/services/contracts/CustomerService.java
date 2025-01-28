package com.eldar.person_service.services.contracts;

import com.eldar.person_service.dtos.request.CustomerRequestDTO;
import com.eldar.person_service.dtos.response.CustomerResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface CustomerService {

    List<CustomerResponseDTO> getAll();

    CustomerResponseDTO getById(Long id);

    void add(@Valid CustomerRequestDTO personRequestDTO);

    void update(Long id, CustomerRequestDTO userRequestDTO);

    void delete(Long id);
}
