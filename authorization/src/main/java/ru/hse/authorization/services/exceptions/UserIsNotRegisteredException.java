package ru.hse.authorization.services.exceptions;

public class UserIsNotRegisteredException extends Exception {
    public UserIsNotRegisteredException(String message) {
        super(message);
    }
}
