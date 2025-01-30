package com.eldar.products_service.mappers;

import com.eldar.products_service.dtos.requests.ProductRequestDTO;
import com.eldar.products_service.dtos.responses.ProductResponseDTO;
import com.eldar.products_service.models.Brand;
import com.eldar.products_service.models.Category;
import com.eldar.products_service.models.Product;
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
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Mapping(target = "category",source = "categoryId", qualifiedByName = "mapIdToCategory")
    @Mapping(target = "brand",source = "brandId", qualifiedByName = "mapIdToBrand")
    public abstract Product toEntity(ProductRequestDTO productRequestDTO);

    @Mapping(target = "category",source = "category", qualifiedByName = "mapCategoryToName")
    @Mapping(target = "brand",source = "brand", qualifiedByName = "mapBrandToName")
    public abstract ProductResponseDTO toResponseDTO(Product product);

    @Mapping(target = "category",source = "categoryId", qualifiedByName = "mapIdToCategory")
    @Mapping(target = "brand",source = "brandId", qualifiedByName = "mapIdToBrand")
    public abstract void updateEntity(@MappingTarget Product product, ProductRequestDTO productRequestDTO);

    @Named("mapIdToCategory")
    public Category mapIdToCategory(Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @Named("mapIdToBrand")
    public Brand mapIdToBrand(Long brandId) {
        return brandService.getBrandById(brandId);
    }

    @Named("mapCategoryToName")
    public String mapCategoryToName(Category category ){
        return  category.getName().toUpperCase();
    }

    @Named("mapBrandToName")
    public String mapBrandToName(Brand brand) {
        return  brand.getName().toUpperCase();
    }


}
