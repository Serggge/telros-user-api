package ru.serggge.telros_user_api.service;

import ru.serggge.telros_user_api.model.User;

public interface UserService {

    User add(User user);

    User update(User user, Long userId);

    User get(Long userId);

    void remove(Long idForDelete, Long userId);
}
