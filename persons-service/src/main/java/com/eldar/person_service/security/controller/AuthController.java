package com.eldar.person_service.security.controller;



import com.eldar.person_service.security.dtos.request.LoginRequestDTO;
import com.eldar.person_service.security.dtos.response.LoginResponseDTO;
import com.eldar.person_service.security.service.contracts.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "authentication-controller")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(description = "Login with user and password.", summary = "Login with user and password")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO userLoginRequestDTO, HttpServletRequest httpServletRequest) throws LoginException {
        System.out.println(httpServletRequest.getRequestURL());
        System.out.println(httpServletRequest.getHeader("Referer"));
        return new ResponseEntity<>(authService.attemptUserPasswordLogin(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword()), HttpStatus.OK);
    }

}
