package com.bizanaliza.app.notes.Exception.Tag;

public class TagTooLongException extends RuntimeException{
    public TagTooLongException(String message) {
        super(message);
    }

    public TagTooLongException(String message, Throwable cause) {
        super(message, cause);
    }
}
