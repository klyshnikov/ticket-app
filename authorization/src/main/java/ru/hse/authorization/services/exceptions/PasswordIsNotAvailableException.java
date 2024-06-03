package ru.hse.authorization.services.exceptions;

public class PasswordIsNotAvailableException extends RuntimeException {
    public PasswordIsNotAvailableException(String message) {
        super(message);
    }
}
