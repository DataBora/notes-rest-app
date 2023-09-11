package com.bizanaliza.app.notes.Exception.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNameTooLongException.class)
    public ResponseEntity<Object> handleUserNameTooLong(UserNameTooLongException userNameTooLongException){
        UserException userException = new UserException(
                userNameTooLongException.getMessage(),
                userNameTooLongException.getCause(),
                HttpStatus.FORBIDDEN
        );
        return  new ResponseEntity<>(userException, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserNicknameTooLongException.class)
    public ResponseEntity<Object> handleUserNicknameTooLong(UserNicknameTooLongException userNicknameTooLongException){
        UserException userException = new UserException(
                userNicknameTooLongException.getMessage(),
                userNicknameTooLongException.getCause(),
                HttpStatus.FORBIDDEN
        );
        return new ResponseEntity<>(userException, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Object> handleUserExists(UserExistsException userExistsException){
        UserException userException =new UserException(
                userExistsException.getMessage(),
                userExistsException.getCause(),
                HttpStatus.CONFLICT
        );
        return new ResponseEntity<>(userException,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException userNotFoundException){
        UserException userException = new UserException(
                userNotFoundException.getMessage(),
                userNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return  new ResponseEntity<>(userException, HttpStatus.NOT_FOUND);
    }


}
