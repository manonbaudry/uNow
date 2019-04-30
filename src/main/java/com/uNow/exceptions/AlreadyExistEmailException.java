package com.uNow.exceptions;

public class AlreadyExistEmailException extends Exception {

    public AlreadyExistEmailException() {
        super();
    }

    public AlreadyExistEmailException(String message) {
        super(message);
    }
}
