package com.eldar.sales_service.dtos.responses;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * */
@Data
@Builder
public class DetailResponseDTO {

    private String productName;

    private BigDecimal productPrice;

    private String brand;

    private Integer quantity;

    private BigDecimal subtotal;

    private Integer discountPercentage;

    private BigDecimal totalAmount;

}
