package com.bizanaliza.app.notes.Exception.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TagExceptionHandler{

    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<Object> handleTagNotFound(TagNotFoundException tagNotFoundException){
        TagException tagException = new TagException(
                tagNotFoundException.getMessage(),
                tagNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(tagException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TagTooLongException.class)
    public ResponseEntity<Object> handleTagTooLong(TagTooLongException tagTooLongException){
        TagException tagException = new TagException(
                tagTooLongException.getMessage(),
                tagTooLongException.getCause(),
                HttpStatus.FORBIDDEN
        );
        return new ResponseEntity<>(tagException, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(TagAlreadyExistsException.class)
    public ResponseEntity<Object> handleTagAlreadyExists(TagAlreadyExistsException tagAlreadyExistsException){
        TagException tagException = new TagException(
                tagAlreadyExistsException.getMessage(),
                tagAlreadyExistsException.getCause(),
                HttpStatus.FORBIDDEN
        );
        return new ResponseEntity<>(tagException, HttpStatus.FORBIDDEN);
    }
}
