package ru.serggge.telros_user_api.handler.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundException extends UsernameNotFoundException {

    public UserNotFoundException(String exception) {
        super(exception);
    }
}
