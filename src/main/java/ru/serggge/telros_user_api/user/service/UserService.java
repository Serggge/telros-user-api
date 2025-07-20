package ru.serggge.telros_user_api.user.service;

import ru.serggge.telros_user_api.register.entity.Credential;
import ru.serggge.telros_user_api.user.entity.User;
import java.util.List;

public interface UserService {

    void createGuest(Credential credential);

    User add(User user, String login);

    User update(User user, Long userId);

    User get(Long userId);

    List<User> getAll();

    void remove(Long idForDelete, Long userId);

    User getByIdEager(Long userId);

    List<User> getAllEager();
}
