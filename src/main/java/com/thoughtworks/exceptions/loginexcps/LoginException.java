package com.thoughtworks.exceptions.loginexcps;

public class LoginException extends Exception{
    public LoginException() {
        super();
    }

    public LoginException(String msg) {
        super(msg);
    }
}
