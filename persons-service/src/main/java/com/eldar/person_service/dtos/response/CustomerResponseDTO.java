package com.eldar.person_service.dtos.response;


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
