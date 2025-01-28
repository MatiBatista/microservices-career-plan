package com.eldar.products_service.dtos.requests;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequestDTO {

    private String name;

    private BigDecimal price;

    private Integer stock;

    private Long categoryId;

    private Long brandId;

}
