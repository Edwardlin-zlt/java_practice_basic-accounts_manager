package com.thoughtworks.exceptions.loginexcps;

public class WrongPasswordException extends LogInException{
    public WrongPasswordException() {
        super();
    }

    public WrongPasswordException(String msg) {
        super(msg);
    }
}
