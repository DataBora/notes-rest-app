package com.bizanaliza.app.notes.Exception.User;

public class UserNicknameTooLongException extends RuntimeException{
    public UserNicknameTooLongException(String message) {
        super(message);
    }

    public UserNicknameTooLongException(String message, Throwable cause) {
        super(message, cause);
    }
}
