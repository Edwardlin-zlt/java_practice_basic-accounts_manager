package com.thoughtworks.exceptions.loginexcps;

public class WrongPasswordException extends LoginException {
    public WrongPasswordException() {
        super();
    }

    public WrongPasswordException(String msg) {
        super(msg);
    }
}
