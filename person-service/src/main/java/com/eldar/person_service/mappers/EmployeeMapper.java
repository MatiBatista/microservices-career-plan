package com.eldar.person_service.mappers;

import com.eldar.person_service.dtos.request.EmployeeRequestDTO;
import com.eldar.person_service.dtos.response.EmployeeResponseDTO;
import com.eldar.person_service.models.Employee;
import com.eldar.person_service.services.contracts.EncryptService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    @Autowired
    private EncryptService encryptService;

    @Mapping(target = "password", qualifiedByName = "mapPassword")
    public abstract Employee toEntity(EmployeeRequestDTO employeeRequestDTO);

    public abstract EmployeeResponseDTO toResponseDTO(Employee employee);

    @Mapping(target = "password", qualifiedByName = "mapPassword")
    public abstract void updateEntity(@MappingTarget Employee user, EmployeeRequestDTO employeeRequestDTO);

    @Named("mapPassword")
    public String mapPassword(String password) {return encryptService.encryptPassword(password);}

}
