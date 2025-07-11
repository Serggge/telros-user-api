package ru.serggge.telros_user_api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.serggge.telros_user_api.exception.EntityArgumentException;
import ru.serggge.telros_user_api.exception.SecurityAccessException;
import ru.serggge.telros_user_api.exception.UserNotFoundException;
import ru.serggge.telros_user_api.model.Role;
import ru.serggge.telros_user_api.model.RoleType;
import ru.serggge.telros_user_api.model.User;
import ru.serggge.telros_user_api.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public User add(User user) {
        checkDuplicateEmail(user);
        RoleType defaultRole = roleService.getRole(Role.USER);
        user.setRole(defaultRole);
        User createdUser = userRepository.save(user);
        log.info("User created: {}", createdUser);
        return createdUser;
    }

    @Override
    public User update(User user, Long userId) {
        checkUserExistence(userId);
        user.setId(userId);
        User editedUser = userRepository.save(user);
        log.info("User updated: {}", editedUser);
        return editedUser;
    }

    @Override
    public User get(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id: <%d> not found", userId)));
    }

    @Override
    public void remove(Long idForDelete, Long userId) {
        checkOwner(idForDelete, userId);
        User user = get(userId);
        userRepository.deleteById(userId);
        log.info("User deleted: {}", user);
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

    private void checkOwner(Long userId, Long ownerId) {
        if (userId == null || !userId.equals(ownerId)) {
            throw new SecurityAccessException("The user does not have permission to perform this operation");
        }
    }
}
