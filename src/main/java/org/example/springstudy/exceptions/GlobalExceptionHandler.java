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
    public ResponseEntity<ErrorResponse> handleInvalidTransactionTypeException(InvalidTransactionTypeException e) {
        // Create an Error Response Object with the relevant details
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.INVALID_TRANSACTION_TYPE,
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.GENERIC_ERROR,
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientFundsException(InsufficientFundsException e) {
         ErrorResponse errorResponse = new ErrorResponse(
                 ErrorCode.INSUFFICIENT_FUNDS,
                 e.getMessage(),
                 HttpStatus.BAD_REQUEST.value()
         );
         return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                ErrorCode.ILLEGAL_ARGUMENT,
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

//Why Does Spring Handle This Automatically?
//	•	The @ControllerAdvice annotation is tied to the DispatcherServlet, meaning:
//	•	If any controller throws an exception, the DispatcherServlet knows to delegate it to the GlobalExceptionHandler.
//	•	Once the GlobalExceptionHandler provides a ResponseEntity, the DispatcherServlet takes care of converting it to the HTTP response format and sending it to the client.