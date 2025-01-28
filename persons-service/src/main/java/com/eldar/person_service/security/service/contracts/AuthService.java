package com.eldar.person_service.security.service.contracts;


import com.eldar.person_service.security.dtos.response.LoginResponseDTO;

import javax.security.auth.login.LoginException;

public interface AuthService {

   LoginResponseDTO attemptUserPasswordLogin(String user, String password) throws LoginException;

}
