package com.thoughtworks.exceptions.registerexcps;

public class RegisterException extends Exception {
    public RegisterException() {
        super();
    }
    public RegisterException(String msg) {
        super(msg);
    }

    public static class IllegalPasswordException extends RegisterException {
        public IllegalPasswordException() {
            super();
        }

        public IllegalPasswordException(String msg) {
            super(msg);
        }
    }
}
