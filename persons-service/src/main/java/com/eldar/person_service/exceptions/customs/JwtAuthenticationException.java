package com.eldar.person_service.exceptions.customs;

public class JwtAuthenticationException extends RuntimeException {

    public JwtAuthenticationException() {super();}

    public JwtAuthenticationException(String message) {super(message);}
}
