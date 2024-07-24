package com.kirtar.web.lab4.services.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationException extends Exception{
    private String errorMessage;
    public RegistrationException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
