package ru.serggge.telros_user_api.handler.exception;

public class SecurityAccessException extends SecurityException {

    public SecurityAccessException(String message) {
        super(message);
    }
}
