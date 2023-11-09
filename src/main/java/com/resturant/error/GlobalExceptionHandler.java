package com.resturant.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorNotFoundException.class)
    public ResponseEntity<?> exceptionNotFound(ErrorNotFoundException notFoundException){
        ErrorResponse errorResponse = new ErrorResponse(notFoundException.getLocalizedMessage()
                                                        ,Arrays.asList(notFoundException.getMessage()));

        return  ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(DublicateException.class)
    public ResponseEntity<?> exceptionDublicate(DublicateException notFoundException){
        ErrorResponse errorResponse = new ErrorResponse(notFoundException.getLocalizedMessage()
                ,Arrays.asList(notFoundException.getMessage()));

        return  ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }


}
