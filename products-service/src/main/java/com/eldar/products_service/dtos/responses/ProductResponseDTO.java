package com.eldar.products_service.dtos.responses;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDTO {

    private Long id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private CategoryResponse category;

    private BrandResponse brand;

    @Builder
    public static class CategoryResponse{
        private String name;
        private String parentCategory;
    }

    @Builder
    public static class BrandResponse{
        private String name;
    }
}
