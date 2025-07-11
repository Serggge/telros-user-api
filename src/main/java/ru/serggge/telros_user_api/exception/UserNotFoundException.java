package ru.serggge.telros_user_api.exception;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String exception) {
        super(exception);
    }
}
