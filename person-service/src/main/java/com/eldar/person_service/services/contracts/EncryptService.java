package com.eldar.person_service.services.contracts;

public interface EncryptService {

    String encryptPassword(String password);

    Boolean verifyPassword(String password, String encryptedPassword);
}
