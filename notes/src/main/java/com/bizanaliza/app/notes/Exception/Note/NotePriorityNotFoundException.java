package com.bizanaliza.app.notes.Exception.Note;

public class NotePriorityNotFoundException extends RuntimeException{

    public NotePriorityNotFoundException(String message) {
        super(message);
    }

    public NotePriorityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
