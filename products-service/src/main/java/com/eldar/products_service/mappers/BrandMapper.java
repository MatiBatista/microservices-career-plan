package com.eldar.products_service.mappers;

import com.eldar.products_service.dtos.requests.BrandRequestDTO;
import com.eldar.products_service.dtos.requests.CategoryRequestDTO;
import com.eldar.products_service.dtos.responses.BrandResponseDTO;
import com.eldar.products_service.dtos.responses.CategoryResponseDTO;
import com.eldar.products_service.models.Brand;
import com.eldar.products_service.models.Category;
import com.eldar.products_service.services.contracts.CategoryService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public interface BrandMapper {


     Brand toEntity(BrandRequestDTO brandRequestDTO);

     BrandResponseDTO toResponseDTO(Brand brand);

     void updateEntity(@MappingTarget Brand brand, BrandRequestDTO brandRequestDTO);
}
