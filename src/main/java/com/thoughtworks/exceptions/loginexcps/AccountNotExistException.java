package com.thoughtworks.exceptions.loginexcps;

import javax.security.auth.login.LoginException;

public class AccountNotExistException extends LoginException {
    public AccountNotExistException() {
        super();
    }

    public AccountNotExistException(String msg) {
        super(msg);
    }
}
