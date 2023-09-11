package com.bizanaliza.app.notes.Exception.Note;

public class NoteTitleTooLongException extends RuntimeException{
    public NoteTitleTooLongException(String message) {
        super(message);
    }

    public NoteTitleTooLongException(String message, Throwable cause) {
        super(message, cause);
    }
}
