package ru.serggge.service;

import ru.serggge.entity.User;
import java.util.List;

public interface UserService {

    User add(User user);

    User update(User user, Long userId);

    User get(Long userId);

    List<User> getAll();

    void remove(Long idForDelete, Long userId);

    User getById(Long userId);
}