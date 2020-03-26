package com.thoughtworks.exceptions.registerexcps;

public class PasswordIllegalException extends RegisterException {
    public PasswordIllegalException() {

    }
    public PasswordIllegalException(String msg){
        super(msg);
    }
}
