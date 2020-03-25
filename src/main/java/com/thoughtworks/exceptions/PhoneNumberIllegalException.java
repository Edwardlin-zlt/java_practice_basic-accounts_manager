package com.thoughtworks.exceptions;

public class PhoneNumberIllegalException extends RegisterException {
    public PhoneNumberIllegalException() {
        super();
    }

    public PhoneNumberIllegalException(String msg) {
        super(msg);
    }
}