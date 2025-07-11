package ru.serggge.telros_user_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.serggge.telros_user_api.exception.EntityArgumentException;
import ru.serggge.telros_user_api.exception.UserNotFoundException;
import ru.serggge.telros_user_api.model.User;
import ru.serggge.telros_user_api.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User add(User user) {
        checkDuplicateEmail(user);
        return userRepository.save(user);
    }

    @Override
    public User update(User user, Long userId) {
        checkUserExistence(userId);
        user.setId(userId);
        return userRepository.save(user);
    }

    @Override
    public User get(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id: <%d> not found", userId)));
    }

    @Override
    public void remove(Long userId, Long adminId) {

        userRepository.deleteById(userId);
    }

    private void checkDuplicateEmail(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityArgumentException(String.format("User with email <%s> already registered", user.getEmail()));
        }
    }

    private void checkUserExistence(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(String.format("User with id: <%d> not found", userId));
        }
    }
}
