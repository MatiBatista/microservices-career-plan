package com.eldar.sales_service.rest_service.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String firstname;

    @NotBlank(message = "Lastname is required")
    @Size(min = 2, max = 50, message = "Lastname must be between 2 and 50 characters")
    private String lastname;

    @NotBlank(message = "DNI is required")
    @Size(min = 8, message = "DNI must have at least 8 digits")
    private String dni;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 10, max = 255, message = "Address must be between 10 and 255 characters")
    private String address;

}
