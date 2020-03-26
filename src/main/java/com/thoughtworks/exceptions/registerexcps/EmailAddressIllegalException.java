package com.thoughtworks.exceptions.registerexcps;

public class EmailAddressIllegalException extends RegisterException {
    public EmailAddressIllegalException() {
        super();
    }

    public EmailAddressIllegalException(String msg) {
        super(msg);
    }
}
