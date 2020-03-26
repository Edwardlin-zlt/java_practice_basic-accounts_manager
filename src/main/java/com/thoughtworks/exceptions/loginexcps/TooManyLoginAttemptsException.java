package com.thoughtworks.exceptions.loginexcps;

public class TooManyLoginAttemptsException extends LogInException{
    public TooManyLoginAttemptsException() {
        super();
    }

    public TooManyLoginAttemptsException(String msg) {
        super(msg);
    }
}
