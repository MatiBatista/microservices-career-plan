package com.eldar.products_service.dtos.responses;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponseDTO {

    private Long id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private String category;

    private String brand;
}
