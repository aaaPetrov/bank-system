package com.solvd.banksystem.exception;

public class InvalidHumanDataException extends Exception {

    public InvalidHumanDataException(String message) {
        super(message);
    }

    public InvalidHumanDataException(String message, Throwable cause) {
        super(message, cause);
    }

}
