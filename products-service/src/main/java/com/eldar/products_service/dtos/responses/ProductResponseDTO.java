package com.eldar.products_service.dtos.responses;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    public static class CategoryResponse{
        private String name;
        private String parentCategory;
    }

    @Builder
    @Data
    public static class BrandResponse{
        private String name;
    }
}
