package com.leaderboard.api.exceptionhandler;

import org.apache.coyote.BadRequestException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class NewExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class, BadRequestException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        int statusCode = ex instanceof ResourceNotFoundException ? HttpStatus.NOT_FOUND.value() : HttpStatus.BAD_REQUEST.value();
        return ResponseEntity.status(statusCode).body(new ErrorResponse() {
            @Override
            public HttpStatusCode getStatusCode() {
                return null;
            }

            @Override
            public ProblemDetail getBody() {
                return null;
            }
        });
    }
}
