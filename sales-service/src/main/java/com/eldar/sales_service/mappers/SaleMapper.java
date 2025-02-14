package com.eldar.sales_service.mappers;

import com.eldar.sales_service.dtos.responses.SaleResponseDTO;
import com.eldar.sales_service.dtos.responses.SaleResponseDTOAll;
import com.eldar.sales_service.models.Sale;
import com.eldar.sales_service.rest_service.dtos.responses.CustomerResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class SaleMapper {

   // @Autowired
    //private CategoryRepository categoryRepository;

   // @Mapping(target = "parentCategory",source = "parentCategoryId", qualifiedByName = "mapIdToParentCategory")
   // public abstract Sale toEntity(CategoryRequestDTO categoryRequestDTO);

    public abstract SaleResponseDTOAll toResponseDTOAll(Sale category, CustomerResponseDTO customerResponseDTO);

   // @Mapping(target = "parentCategory",source = "parentCategory", qualifiedByName = "mapParentCategoryToName")
    public abstract SaleResponseDTO toResponseDTO(Sale category);

   // @Mapping(target = "parentCategory",source = "parentCategoryId", qualifiedByName = "mapIdToParentCategory")
   // public abstract void updateEntity(@MappingTarget Category category, CategoryRequestDTO categoryRequestDTO);

}
