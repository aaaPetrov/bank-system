package com.solvd.homework7.exception;

public class InvalidCurrencyTypeException extends RuntimeException {

    public InvalidCurrencyTypeException(String message) {
        super(message);
    }

    public InvalidCurrencyTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}
