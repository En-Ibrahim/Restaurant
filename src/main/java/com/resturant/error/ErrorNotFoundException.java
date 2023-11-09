package com.resturant.error;

public class ErrorNotFoundException extends RuntimeException{

    public ErrorNotFoundException() {
    }

    public ErrorNotFoundException(String message) {
        super(message);
    }
}
