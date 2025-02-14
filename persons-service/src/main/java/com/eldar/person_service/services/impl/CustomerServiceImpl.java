package com.eldar.person_service.services.impl;

import com.eldar.person_service.dtos.request.CustomerRequestDTO;
import com.eldar.person_service.dtos.response.CustomerResponseDTO;
import com.eldar.person_service.exceptions.customs.BadRequestException;
import com.eldar.person_service.exceptions.customs.NotFoundException;
import com.eldar.person_service.mappers.CustomerMapper;
import com.eldar.person_service.models.Customer;
import com.eldar.person_service.repositories.CustomerRepository;
import com.eldar.person_service.services.contracts.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerResponseDTO> getAll() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toResponseDTO).toList();
    }

    @Override
    public CustomerResponseDTO getById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::toResponseDTO)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));
    }

    @Override
    @Transactional
    public void add(CustomerRequestDTO customerRequestDTO) {
        validateCustomerRequestAdd(customerRequestDTO);
        customerRepository.save(customerMapper.toEntity(customerRequestDTO));
    }


    @Override
    @Transactional
    public void update(Long id, CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
        validateCustomerRequestUpdate(customer, customerRequestDTO);
        customerMapper.updateEntity(customer, customerRequestDTO);
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new NotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }


    private void validateCustomerRequestAdd(CustomerRequestDTO customerRequestDTO) {
        Optional<Customer> existingCustomer = customerRepository.findByDniOrEmail(
                customerRequestDTO.getDni(),
                customerRequestDTO.getEmail()
        );
        if (existingCustomer.isPresent()) {
            Customer customer = existingCustomer.get();
            if (customer.getDni().equals(customerRequestDTO.getDni())) {
                throw new BadRequestException("Dni is already in use");
            }
            if (customer.getEmail().equals(customerRequestDTO.getEmail())) {
                throw new BadRequestException("Email is already in use");
            }
        }
    }

    private void validateCustomerRequestUpdate(Customer customer,CustomerRequestDTO customerRequestDTO) {
        Optional<Customer> existingCustomer = customerRepository.findByDniOrEmail(
                customerRequestDTO.getDni(),
                customerRequestDTO.getEmail()
        );
        if (existingCustomer.isPresent()) {
            Customer existsCustomer= existingCustomer.get();

            if (existsCustomer.getDni().equals(customerRequestDTO.getDni()) && !Objects.equals(customer.getDni(), customerRequestDTO.getDni())) {
                throw new BadRequestException("Dni is already in use");
            }
            if (existsCustomer.getEmail().equals(customerRequestDTO.getEmail()) && !Objects.equals(customer.getEmail(), customerRequestDTO.getEmail())) {
                throw new BadRequestException("Email is already in use");
            }
        }
    }
}
