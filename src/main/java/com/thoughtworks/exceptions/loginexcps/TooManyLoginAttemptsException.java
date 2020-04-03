package com.thoughtworks.exceptions.loginexcps;

public class TooManyLoginAttemptsException extends LoginException {
    public TooManyLoginAttemptsException() {
        super();
    }

    public TooManyLoginAttemptsException(String msg) {
        super(msg);
    }
}
