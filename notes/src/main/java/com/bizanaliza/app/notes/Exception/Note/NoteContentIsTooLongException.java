package com.bizanaliza.app.notes.Exception.Note;

public class NoteContentIsTooLongException extends RuntimeException{

    public NoteContentIsTooLongException(String message) {
        super(message);
    }

    public NoteContentIsTooLongException(String message, Throwable cause) {
        super(message, cause);
    }
}
