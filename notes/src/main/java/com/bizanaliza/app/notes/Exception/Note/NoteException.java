package com.bizanaliza.app.notes.Exception.Note;

import org.springframework.http.HttpStatus;

public class NoteException {

    private final String errorMessage;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public NoteException(String errorMessage, Throwable throwable, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
