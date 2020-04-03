package com.thoughtworks.exceptions;

public class UserInputFormatException extends Exception {
    public UserInputFormatException() {

    }

    public UserInputFormatException(String msg) {
        super(msg);
    }
}
