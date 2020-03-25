package com.thoughtworks.exceptions;

public class PasswordIllegalException extends RegisterException{
    public PasswordIllegalException() {

    }
    public PasswordIllegalException(String msg){
        super(msg);
    }
}
