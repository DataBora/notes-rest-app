package com.bizanaliza.app.notes.Exception.Note;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoteExceptionHandler {

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<Object> handleNoteNotFound(NoteNotFoundException noteNotFoundException){
        NoteException noteException = new NoteException(
                noteNotFoundException.getMessage(),
                noteNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(noteException,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoteUnauthorizedException.class)
    public ResponseEntity<Object> handleNoteUnauthorized(NoteUnauthorizedException noteUnauthorizedException){
        NoteException noteException = new NoteException(
                noteUnauthorizedException.getMessage(),
                noteUnauthorizedException.getCause(),
                HttpStatus.UNAUTHORIZED
        );
        return new ResponseEntity<>(noteException,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoteTitleTooLongException.class)
    public ResponseEntity<Object> handleNoteTitleTooLong(NoteTitleTooLongException noteTitleTooLongException){
        NoteException noteException = new NoteException(
                noteTitleTooLongException.getMessage(),
                noteTitleTooLongException.getCause(),
                HttpStatus.FORBIDDEN
        );
        return new ResponseEntity<>(noteException, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoteContentIsTooLongException.class)
    public ResponseEntity<Object> handleNoteContentTooLong(NoteContentIsTooLongException noteContentIsTooLongException){
        NoteException noteException = new NoteException(
                noteContentIsTooLongException.getMessage(),
                noteContentIsTooLongException.getCause(),
                HttpStatus.FORBIDDEN
        );
        return new ResponseEntity<>(noteException,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotePriorityNotFoundException.class)
    public ResponseEntity<Object> handleNotePriorityNotFound(NotePriorityNotFoundException notePriorityNotFoundException) {
        NoteException noteException = new NoteException(
                notePriorityNotFoundException.getMessage(),
                notePriorityNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(noteException, HttpStatus.NOT_FOUND);
    }


}

