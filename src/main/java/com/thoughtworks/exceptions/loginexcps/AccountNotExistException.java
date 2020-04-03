package com.thoughtworks.exceptions.loginexcps;

public class AccountNotExistException extends LoginException {
    public AccountNotExistException() {
        super();
    }

    public AccountNotExistException(String msg) {
        super(msg);
    }
}
