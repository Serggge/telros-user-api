package ru.serggge.telros_user_api.handler.exception;

public class EntityArgumentException extends IllegalArgumentException {

    public EntityArgumentException(String message) {
        super(message);
    }
}