package com.eldar.products_service.services.impl;


import com.eldar.products_service.services.contracts.EncryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncryptServiceImpl implements EncryptService {

    @Override
    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public Boolean verifyPassword(String password, String encryptedPassword) {
        return BCrypt.checkpw(password,encryptedPassword);
    }
}
