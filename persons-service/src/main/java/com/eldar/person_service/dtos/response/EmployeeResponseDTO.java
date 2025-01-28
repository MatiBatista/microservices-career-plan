package com.eldar.person_service.dtos.response;


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
