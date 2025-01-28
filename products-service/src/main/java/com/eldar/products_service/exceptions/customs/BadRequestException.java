package com.eldar.products_service.exceptions.customs;

public class BadRequestException extends RuntimeException{

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}
