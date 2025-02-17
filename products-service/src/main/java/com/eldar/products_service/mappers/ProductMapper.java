package com.eldar.products_service.mappers;

import com.eldar.products_service.dtos.requests.ProductRequestDTO;
import com.eldar.products_service.dtos.responses.ProductResponseDTO;
import com.eldar.products_service.exceptions.customs.NotFoundException;
import com.eldar.products_service.models.Brand;
import com.eldar.products_service.models.Category;
import com.eldar.products_service.models.Product;
import com.eldar.products_service.repositories.BrandRepository;
import com.eldar.products_service.repositories.CategoryRepository;
import com.eldar.products_service.services.contracts.BrandService;
import com.eldar.products_service.services.contracts.CategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Mapping(target = "category",source = "categoryId", qualifiedByName = "mapIdToCategory")
    @Mapping(target = "brand",source = "brandId", qualifiedByName = "mapIdToBrand")
    public abstract Product toEntity(ProductRequestDTO productRequestDTO);

    @Mapping(target = "category",source = "category", qualifiedByName = "mapCategoryToCategoryResponse")
    @Mapping(target = "brand",source = "brand", qualifiedByName = "mapBrandToBrandResponse")
    public abstract ProductResponseDTO toResponseDTO(Product product);

    @Mapping(target = "category",source = "categoryId", qualifiedByName = "mapIdToCategory")
    @Mapping(target = "brand",source = "brandId", qualifiedByName = "mapIdToBrand")
    public abstract void updateEntity(@MappingTarget Product product, ProductRequestDTO productRequestDTO);

    @Named("mapCategoryToCategoryResponse")
    public ProductResponseDTO.CategoryResponse mapCategoryToCategoryResponse(Category category) {
        return ProductResponseDTO.CategoryResponse.builder()
                .name(category.getName())
                .parentCategory(category.getParentCategory() != null ? category.getParentCategory().getName() : null)
                .build();
    }

    @Named("mapBrandToBrandResponse")
    public ProductResponseDTO.BrandResponse mapBrandToBrandResponse(Brand brand) {
        return ProductResponseDTO.BrandResponse.builder()
                .name(brand.getName())
                .build();
    }

    @Named("mapIdToCategory")
    public Category mapIdToCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(()-> new NotFoundException("Category not found with "+categoryId+ "not found"));
    }

    @Named("mapIdToBrand")
    public Brand mapIdToBrand(Long brandId) {
        return brandRepository.findById(brandId).orElseThrow(()-> new NotFoundException("Brand not found with "+brandId+ "not found"));
    }

    @Named("mapCategoryToId")
    public Long mapCategoryToName(Category category ){
        return  category.getId();
    }

    @Named("mapBrandToId")
    public Long mapBrandToName(Brand brand) {
        return  brand.getId();
    }


}
