package com.thoughtworks;

public class PhoneNumberIllegalException extends RegisterException {
    public PhoneNumberIllegalException() {
        super();
    }

    public PhoneNumberIllegalException(String msg) {
        super(msg);
    }
}
