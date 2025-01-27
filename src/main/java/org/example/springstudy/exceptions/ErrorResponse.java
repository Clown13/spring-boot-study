package org.example.springstudy.exceptions;

import java.time.LocalDateTime;
// Creating class that will structure error details returned to the client. This will hold timestamp, error code, error message, and HTTP status.
//This will standardize error format. Every time an exception is thrown, Spring sends exception to GlobalExecution Handler, and  ErrorResponse object will be created and returned to client as HTTP Response body.
public class ErrorResponse {
    private LocalDateTime timestamp;
    private ErrorCode errorCode;
    private String errorMessage;
    private int status;

    public ErrorResponse(ErrorCode errorCode, String errorMessage, int status) {
        this.timestamp = LocalDateTime.now();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.status = status;

    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public int getStatus() {
        return status;
    }

}
