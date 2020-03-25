package com.thoughtworks;

public class EmailAddressIllegalException extends RegisterException {
    public EmailAddressIllegalException() {
        super();
    }

    public EmailAddressIllegalException(String msg) {
        super(msg);
    }
}
