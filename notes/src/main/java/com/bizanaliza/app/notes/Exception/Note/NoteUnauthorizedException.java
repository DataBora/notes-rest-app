package com.bizanaliza.app.notes.Exception.Note;

public class NoteUnauthorizedException extends RuntimeException{

    public NoteUnauthorizedException(String message) {
        super(message);
    }

    public NoteUnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
