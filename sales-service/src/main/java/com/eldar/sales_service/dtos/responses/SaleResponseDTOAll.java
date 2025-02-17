package com.eldar.sales_service.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleResponseDTOAll {

    private Long id;

    private LocalDateTime date;

    private BigDecimal totalAmount;

    private Integer discountPercentage;

    private BigDecimal discountAmount;

    private CustomerResponse customer;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class CustomerResponse{

        private String firstname;

        private String lastname;

        private String dni;

        protected String email;

        protected String address;
    }

}
