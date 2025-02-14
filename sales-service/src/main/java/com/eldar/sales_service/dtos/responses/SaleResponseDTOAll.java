package com.eldar.sales_service.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SaleResponseDTOAll {

    private Long id;

    private LocalDateTime date;

    private BigDecimal totalAmount;

    private Integer discountPercentage;

    private BigDecimal discountAmount;

    private CustomerResponse customer;


    public static class CustomerResponse{

        private String firstname;

        private String lastname;

        private String dni;

        protected String email;

        protected String address;
    }

}
