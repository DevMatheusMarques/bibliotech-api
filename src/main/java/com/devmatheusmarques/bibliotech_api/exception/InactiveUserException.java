package com.devmatheusmarques.bibliotech_api.exception;

public class InactiveUserException extends RuntimeException {
    public InactiveUserException(String message) {
        super(message);
    }
}

