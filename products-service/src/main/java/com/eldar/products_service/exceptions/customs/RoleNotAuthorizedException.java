package com.eldar.products_service.exceptions.customs;

public class RoleNotAuthorizedException extends RuntimeException{

    public RoleNotAuthorizedException() {super();}

    public RoleNotAuthorizedException(String message) {super(message);}
}
