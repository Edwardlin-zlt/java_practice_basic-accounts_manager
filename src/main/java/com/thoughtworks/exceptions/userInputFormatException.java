package com.thoughtworks.exceptions;

public class userInputFormatException extends RegisterException {
    public userInputFormatException() {

    }

    public userInputFormatException(String msg) {
        super(msg);
    }
}
