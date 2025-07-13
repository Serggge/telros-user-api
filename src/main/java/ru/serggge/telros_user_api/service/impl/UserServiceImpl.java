package ru.serggge.telros_user_api.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.serggge.telros_user_api.exception.EntityArgumentException;
import ru.serggge.telros_user_api.exception.SecurityAccessException;
import ru.serggge.telros_user_api.exception.UserNotFoundException;
import ru.serggge.telros_user_api.model.RoleType;
import ru.serggge.telros_user_api.model.entity.Role;
import ru.serggge.telros_user_api.model.entity.User;
import ru.serggge.telros_user_api.repository.UserRepository;
import ru.serggge.telros_user_api.service.RoleService;
import ru.serggge.telros_user_api.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    @Transactional
    public User add(User user) {
        checkDuplicateEmail(user);
        Role defaultRole = roleService.getRole(RoleType.USER);
        user.setRole(defaultRole);
        User createdUser = userRepository.save(user);
        log.info("User created: {}", createdUser);
        return createdUser;
    }

    @Override
    @Transactional
    public User update(User user, Long userId) {
        checkUserExistence(userId);
        user.setId(userId);
        User editedUser = userRepository.save(user);
        log.info("User updated: {}", editedUser);
        return editedUser;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public User get(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id: <%d> not found", userId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void remove(Long idForDelete, Long userId) {
        checkOwner(idForDelete, userId);
        User user = get(userId);
        userRepository.deleteById(userId);
        log.info("User deleted: {}", user);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public User getByIdEager(Long userId) {
        return userRepository.findByIdWithFullInfo(userId).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id: <%d> not found", userId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllEager() {
        return userRepository.findAllWithFullInfo();
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
