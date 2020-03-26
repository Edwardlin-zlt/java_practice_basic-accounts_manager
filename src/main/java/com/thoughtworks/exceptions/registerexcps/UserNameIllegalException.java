package com.thoughtworks.exceptions.registerexcps;

import com.thoughtworks.exceptions.registerexcps.RegisterException;

public class UserNameIllegalException extends RegisterException {
    public UserNameIllegalException() {
        super();
    }

    public UserNameIllegalException(String msg) {
        super(msg);
    }
}
