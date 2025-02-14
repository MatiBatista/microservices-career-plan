package com.eldar.sales_service.rest_service.dtos.responses;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeResponseDTO {

    private Long id;

    private String firstname;

    private String lastname;

    private String dni;
}
