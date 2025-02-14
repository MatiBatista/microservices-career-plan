package com.eldar.sales_service.dtos.requests;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleRequestDTO {

    private Long customer_id;

    private Long employee_id;

    private List<DetailRequestDTO> details_products;

    private Integer discountPercentage;

    private LocalDateTime date;

}
