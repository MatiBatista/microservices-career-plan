package com.eldar.person_service.exceptions.customs;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super();
    }

    public NotFoundException(String message){
        super(message);
    }
}
