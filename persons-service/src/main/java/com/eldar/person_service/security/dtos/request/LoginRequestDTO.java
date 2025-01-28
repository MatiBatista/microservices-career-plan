package com.eldar.person_service.security.dtos.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDTO {

    private String username;

    private String password;
}
