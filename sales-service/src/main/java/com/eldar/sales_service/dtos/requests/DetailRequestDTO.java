package com.eldar.sales_service.dtos.requests;

import com.eldar.sales_service.models.Sale;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetailRequestDTO {

    private Long product_id;

    private Integer discountPercentage;

    private Integer quantity;

}
