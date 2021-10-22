package com.solvd.homework7.exception;

public class InvalidClientAgeException extends RuntimeException {

    public InvalidClientAgeException(String message) {
        super(message);
    }

    public InvalidClientAgeException(String message, Throwable cause) {
        super(message, cause);
    }

}
