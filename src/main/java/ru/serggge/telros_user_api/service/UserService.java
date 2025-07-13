package ru.serggge.telros_user_api.service;

import ru.serggge.telros_user_api.model.entity.User;
import java.util.List;

public interface UserService {

    User add(User user);

    User update(User user, Long userId);

    User get(Long userId);

    List<User> getAll();

    void remove(Long idForDelete, Long userId);

    User getByIdEager(Long userId);

    List<User> getAllEager();

}
