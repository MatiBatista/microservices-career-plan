package com.eldar.person_service.mappers;


import com.eldar.person_service.dtos.request.CustomerRequestDTO;
import com.eldar.person_service.dtos.response.CustomerResponseDTO;
import com.eldar.person_service.models.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Person toEntity(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO  toResponseDTO(Person customer);

    void updateEntity(@MappingTarget Person customer, CustomerRequestDTO customerRequestDTO);

}
