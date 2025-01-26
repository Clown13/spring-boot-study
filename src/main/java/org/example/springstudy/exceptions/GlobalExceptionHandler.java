package org.example.springstudy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Global Exception Handler is centralized place where you handle exceptions thrown by your application. Instead of writing try-catch in every controller method, you write single handler for each exception type here.
@ControllerAdvice
public class GlobalExceptionHandler {
    //Controller Advice annotation makes the class capable of intercepting exceptions across all controllers.

    //ExceptionHandler is annotation used to define methods that handle specific exceptions.
    //For Example, when exception like InvalidTransactionTypeException is thrown, Spring will call the corresponding method in the GlobalExceptionHandler Class.
    @ExceptionHandler(InvalidTransactionTypeException.class)
    public ResponseEntity<String> handleInvalidTransactionTypeException(InvalidTransactionTypeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
