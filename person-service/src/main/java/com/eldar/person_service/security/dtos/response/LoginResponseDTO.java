package com.eldar.person_service.security.dtos.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDTO {
    private String accessToken;
}
