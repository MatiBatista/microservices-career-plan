package com.eldar.sales_service.mappers;

import com.eldar.sales_service.dtos.requests.SaleRequestDTO;
import com.eldar.sales_service.dtos.responses.SaleResponseDTO;
import com.eldar.sales_service.dtos.responses.SaleResponseDTOAll;
import com.eldar.sales_service.models.Sale;
import com.eldar.sales_service.rest_service.dtos.responses.CustomerResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public abstract class SaleMapper {

   // @Autowired
    //private CategoryRepository categoryRepository;

   // @Mapping(target = "parentCategory",source = "parentCategoryId", qualifiedByName = "mapIdToParentCategory")
   // public abstract Sale toEntity(CategoryRequestDTO categoryRequestDTO);
   @Mapping(source = "sale.id", target = "id")
   @Mapping(source = "customerResponseDTO",target = "customer", qualifiedByName = "setCustomer")
    public abstract SaleResponseDTOAll toResponseDTOAll(Sale sale, CustomerResponseDTO customerResponseDTO);

   // @Mapping(target = "parentCategory",source = "parentCategory", qualifiedByName = "mapParentCategoryToName")
    public abstract SaleResponseDTO toResponseDTO(Sale category);


   // @Mapping(target = "parentCategory",source = "parentCategoryId", qualifiedByName = "mapIdToParentCategory")
   // public abstract void updateEntity(@MappingTarget Category category, CategoryRequestDTO categoryRequestDTO);

    @Named("setCustomer")
    public SaleResponseDTOAll.CustomerResponse setCustomer (CustomerResponseDTO customerResponseDTO) {
        return SaleResponseDTOAll.CustomerResponse.builder()
                .dni(customerResponseDTO.getDni())
                .email(customerResponseDTO.getEmail())
                .address(customerResponseDTO.getAddress())
                .lastname(customerResponseDTO.getLastname())
                .firstname(customerResponseDTO.getFirstname())
                .build();
    }
}
