package com.eldar.person_service.security.service.impl;

import com.eldar.person_service.exceptions.customs.BadCredentialssException;
import com.eldar.person_service.models.Employee;
import com.eldar.person_service.repositories.EmployeeRepository;
import com.eldar.person_service.security.components.JwtTokenProvider;
import com.eldar.person_service.security.dtos.response.LoginResponseDTO;
import com.eldar.person_service.security.service.contracts.AuthService;
import com.eldar.person_service.services.contracts.EncryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EmployeeRepository employeeRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final EncryptService encryptService;


    @Override
    public LoginResponseDTO attemptUserPasswordLogin(String username, String password) throws LoginException {
        Employee employee = employeeRepository.findByUsername(username).orElseThrow(() -> new BadCredentialssException("Bad credentials exception"));

        if(!encryptService.verifyPassword(password, employee.getPassword())){
            throw new BadCredentialssException("Bad credentials exception");
        }

        return LoginResponseDTO.builder()
                .accessToken(jwtTokenProvider.generateToken(employee))
                .build();
    }
}
