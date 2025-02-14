package com.eldar.person_service.mappers;


import com.eldar.person_service.dtos.request.CustomerRequestDTO;
import com.eldar.person_service.dtos.response.CustomerResponseDTO;
import com.eldar.person_service.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO  toResponseDTO(Customer customer);

    void updateEntity(@MappingTarget Customer customer, CustomerRequestDTO customerRequestDTO);

}
