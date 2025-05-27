package com.example.sharagasystem.exception;

public class NameAlreadyTakenException extends RuntimeException {
    public NameAlreadyTakenException(String message) {
        super(message);
    }
}
