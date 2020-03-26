package com.thoughtworks.exceptions.loginexcps;

public class AccountNotExistException extends LogInException {
    public AccountNotExistException() {
        super();
    }

    public AccountNotExistException(String msg) {
        super(msg);
    }
}
