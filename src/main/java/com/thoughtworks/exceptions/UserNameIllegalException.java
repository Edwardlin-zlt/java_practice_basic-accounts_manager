package com.thoughtworks.exceptions;

public class UserNameIllegalException extends RegisterException {
    public UserNameIllegalException() {
        super();
    }

    public UserNameIllegalException(String msg) {
        super(msg);
    }
}
