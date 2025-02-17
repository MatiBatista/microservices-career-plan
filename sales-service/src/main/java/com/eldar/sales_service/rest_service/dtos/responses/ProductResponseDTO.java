package com.eldar.sales_service.rest_service.dtos.responses;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {

    private Long id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private CategoryResponse category;

    private BrandResponse brand;

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryResponse{
        private String name;
        private String parentCategory;
    }

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BrandResponse{
        private String name;
    }
}

