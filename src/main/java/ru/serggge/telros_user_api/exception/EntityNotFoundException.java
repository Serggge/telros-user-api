package ru.serggge.telros_user_api.exception;

public class EntityNotFoundException extends IllegalArgumentException {

    public EntityNotFoundException(String exception) {
        super(exception);
    }
}
