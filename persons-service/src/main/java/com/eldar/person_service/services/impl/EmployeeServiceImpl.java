package com.eldar.person_service.services.impl;

import com.eldar.person_service.dtos.request.CustomerRequestDTO;
import com.eldar.person_service.dtos.request.EmployeeRequestDTO;
import com.eldar.person_service.dtos.response.CustomerResponseDTO;
import com.eldar.person_service.dtos.response.EmployeeResponseDTO;
import com.eldar.person_service.exceptions.customs.BadRequestException;
import com.eldar.person_service.exceptions.customs.NotFoundException;
import com.eldar.person_service.mappers.EmployeeMapper;
import com.eldar.person_service.models.Employee;
import com.eldar.person_service.models.Person;
import com.eldar.person_service.repositories.EmployeeRepository;
import com.eldar.person_service.services.contracts.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeResponseDTO> getAll() {
        List<Employee> employees = employeeRepository.findAll();

        return employeeRepository.findAll().stream().map(employeeMapper::toResponseDTO).toList();
    }

    @Override
    public EmployeeResponseDTO getById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toResponseDTO)
                .orElseThrow(()->new NotFoundException("Employee not found with id: " + id));
    }

    @Override
    @Transactional
    public void add(EmployeeRequestDTO employeeRequestDTO) {
        validateEmployeeRequestAdd(employeeRequestDTO);
        employeeRepository.save(employeeMapper.toEntity(employeeRequestDTO));
    }

    @Override
    @Transactional
    public void update(Long id, EmployeeRequestDTO employeeRequestDTO) {
        Employee employee= employeeRepository.findById(id).orElseThrow(()-> new NotFoundException("Employee not found"));
        validateEmployeeRequestUpdate(employee,employeeRequestDTO);
        employeeMapper.updateEntity(employee,employeeRequestDTO);
        employeeRepository.save(employee);
    }


    @Override
    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new NotFoundException("Customer not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }


    private void validateEmployeeRequestAdd(EmployeeRequestDTO employeeRequestDTO) {
        Optional<Employee> existingEmployee = employeeRepository.findByDniOrEmailOrUsername(
                employeeRequestDTO.getDni(),
                employeeRequestDTO.getEmail(),
                employeeRequestDTO.getUsername()
        );

        if (existingEmployee.isPresent()) {

            Employee employee = existingEmployee.get();
            if (employee.getDni().equals(employeeRequestDTO.getDni())) {
                throw new BadRequestException("Dni is already in use");
            }
            if (employee.getEmail().equals(employeeRequestDTO.getEmail())) {
                throw new BadRequestException("Email is already in use");
            }
            if (employee.getUsername().equals(employeeRequestDTO.getUsername())) {
                throw new BadRequestException("Username is already in use");
            }
        }
    }


    private void validateEmployeeRequestUpdate(Employee employee, EmployeeRequestDTO employeeRequestDTO) {
        Optional<Employee> existingEmployee = employeeRepository.findByDniOrEmailOrUsername(
                employeeRequestDTO.getDni(),
                employeeRequestDTO.getEmail(),
                employeeRequestDTO.getUsername()
        );
        if (existingEmployee.isPresent()) {

            Employee e= existingEmployee.get();

            if (e.getDni().equals(employeeRequestDTO.getDni()) && !Objects.equals(employee.getDni(), employeeRequestDTO.getDni())) {
                throw new BadRequestException("Dni is already in use");
            }
            if (e.getEmail().equals(employeeRequestDTO.getEmail()) && !Objects.equals(employee.getEmail(), employeeRequestDTO.getEmail())) {
                throw new BadRequestException("Email is already in use");
            }
            if (e.getUsername().equals(employeeRequestDTO.getUsername())&& !Objects.equals(employee.getEmail(), employeeRequestDTO.getEmail())) {
                throw new BadRequestException("Username is already in use");
            }
        }
    }
}
