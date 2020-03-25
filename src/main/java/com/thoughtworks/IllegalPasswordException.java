package com.thoughtworks;

import com.thoughtworks.exceptions.RegisterException;

public class IllegalPasswordException extends RegisterException {
    public IllegalPasswordException() {
        super();
    }

    public IllegalPasswordException(String msg) {
        super(msg);
    }
}
