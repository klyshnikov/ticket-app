package ru.hse.authorization.services.exceptions;

public class SessionIsNotExistedException extends Exception{
    public SessionIsNotExistedException(String message) {
        super(message);
    }
}
