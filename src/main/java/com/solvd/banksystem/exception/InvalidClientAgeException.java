package com.solvd.banksystem.exception;

public class InvalidClientAgeException extends RuntimeException {

    public InvalidClientAgeException(String message) {
        super(message);
    }

    public InvalidClientAgeException(String message, Throwable cause) {
        super(message, cause);
    }

}
