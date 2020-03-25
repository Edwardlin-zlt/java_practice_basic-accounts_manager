package com.thoughtworks;

public class UserNameIllegalException extends RuntimeException {
    public UserNameIllegalException() {
        super();
    }

    public UserNameIllegalException(String msg) {
        super(msg);
    }
}
