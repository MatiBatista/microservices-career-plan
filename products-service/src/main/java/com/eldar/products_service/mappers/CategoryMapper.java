package com.eldar.products_service.mappers;

import com.eldar.products_service.dtos.requests.CategoryRequestDTO;
import com.eldar.products_service.dtos.requests.ProductRequestDTO;
import com.eldar.products_service.dtos.responses.CategoryResponseDTO;
import com.eldar.products_service.dtos.responses.ProductResponseDTO;
import com.eldar.products_service.exceptions.customs.NotFoundException;
import com.eldar.products_service.models.Brand;
import com.eldar.products_service.models.Category;
import com.eldar.products_service.models.Product;
import com.eldar.products_service.repositories.CategoryRepository;
import com.eldar.products_service.services.contracts.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    @Mapping(target = "parentCategory",source = "parentCategoryId", qualifiedByName = "mapIdToParentCategory")
    public abstract Category toEntity(CategoryRequestDTO categoryRequestDTO);

    @Mapping(target = "parentCategory",source = "parentCategory", qualifiedByName = "mapParentCategoryToName")
    public abstract CategoryResponseDTO toResponseDTO(Category category);

    @Mapping(target = "parentCategory",source = "parentCategoryId", qualifiedByName = "mapIdToParentCategory")
    public abstract void updateEntity(@MappingTarget Category category, CategoryRequestDTO categoryRequestDTO);

    @Named("mapIdToParentCategory")
    public Category mapIdToParentCategory(Long parentCategoryId) {
        return categoryRepository.findById(parentCategoryId).orElse(null);
    }

    @Named("mapParentCategoryToName")
    public String mapParentCategoryToName(Category category) {
        if(category!= null) {
            return category.getName();
        }
        return null;
    }


}
