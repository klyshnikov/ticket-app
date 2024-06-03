package ru.hse.authorization.services.exceptions;

public class EmailIsNotAvailableException extends RuntimeException {
    public EmailIsNotAvailableException(String message) {
        super(message);
    }
}
