package ru.hse.ordering.services.exceptions;

public class StationIsNotFoundException extends RuntimeException{
    public StationIsNotFoundException(String message) {
        super(message);
    }
}
