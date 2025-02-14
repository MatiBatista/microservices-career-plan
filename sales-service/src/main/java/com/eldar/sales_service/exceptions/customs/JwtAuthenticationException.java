package com.eldar.sales_service.exceptions.customs;

public class JwtAuthenticationException extends RuntimeException {

    public JwtAuthenticationException() {super();}

    public JwtAuthenticationException(String message) {super(message);}
}
