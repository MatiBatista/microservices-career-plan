package com.eldar.sales_service.rest_service.dtos.responses;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    @Getter
    @Setter
    public static class CategoryResponse{
        private String name;
        private String parentCategory;
    }

    @Builder
    @Getter
    @Setter
    public static class BrandResponse{
        private String name;
    }
}

