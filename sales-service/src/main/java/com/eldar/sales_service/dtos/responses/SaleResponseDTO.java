package com.eldar.sales_service.dtos.responses;



import com.eldar.sales_service.rest_service.dtos.responses.CustomerResponseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleResponseDTO {

    private Long id;

    private LocalDateTime date;

    private BigDecimal totalAmount;

    private Integer discountPercentage;

    private BigDecimal discountAmount;

    private CustomerResponse customer;

    private List<DetailResponseDTO> details;


    public static class CustomerResponse{

        private String firstname;

        private String lastname;

        private String dni;

        protected String email;

        protected String address;
    }


}
