package com.bizanaliza.app.notes.Exception.User;

public class UserNameTooLongException extends RuntimeException{

    public UserNameTooLongException(String message) {
        super(message);
    }

    public UserNameTooLongException(String message, Throwable cause) {
        super(message, cause);
    }
}
