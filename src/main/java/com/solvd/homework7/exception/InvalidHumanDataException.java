package com.solvd.homework7.exception;

public class InvalidHumanDataException extends Exception {

    public InvalidHumanDataException(String message) {
        super(message);
    }

    public InvalidHumanDataException(String message, Throwable cause) {
        super(message, cause);
    }

}
