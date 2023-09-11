package com.bizanaliza.app.notes.Exception.Tag;

public class TagAlreadyExistsException extends RuntimeException{
    public TagAlreadyExistsException(String message) {
        super(message);
    }

    public TagAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
