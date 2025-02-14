package com.eldar.sales_service.rest_service.dtos.responses;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponseDTO {

    private Long id;

    private String firstname;

    private String lastname;

    private String dni;

    protected String email;

    protected String address;

}

