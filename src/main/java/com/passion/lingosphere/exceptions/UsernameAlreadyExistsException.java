package com.passion.lingosphere.exceptions;

public class UsernameAlreadyExistsException extends Exception{
    public UsernameAlreadyExistsException() {
        super();
    }

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
