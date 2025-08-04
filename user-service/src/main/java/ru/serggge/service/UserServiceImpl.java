package ru.serggge.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.serggge.handler.exception.*;
import ru.serggge.entity.User;
import ru.serggge.repository.UserRepository;
import ru.serggge.security.entity.UserAuthorization;
import ru.serggge.security.repository.UserAuthRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserAuthRepository userAuthRepository;

    @Override
    @Transactional
    public User add(User user) {
        // получаем айди зарегистрированного пользователя из контекста
        Long authId = (Long) SecurityContextHolder.getContext()
                                                .getAuthentication()
                                                .getPrincipal();
        // проверяем, не создавал ли уже ранее пользователь свой профиль
        userAuthRepository.findByAuthIdAndIsActiveTrue(authId).ifPresent(usr -> {
                          throw  new RuntimeException("User already registered");
                      });
        // проверяем уникальность электронной почты
        checkDuplicateEmail(user.getEmail());
        // save new user in to database
        User newUser = userRepository.save(user);
        // save his authorization details in to database
        UserAuthorization userAuthorization = new UserAuthorization(null, authId, newUser, true);
        userAuthRepository.save(userAuthorization);
        log.info("User created: {}", newUser);
        return newUser;
    }

    @Override
    @Transactional
    public User update(User user, Long userId) {
        // проверяем, нет ли уже пользователя с таким же логином
        checkUserExistence(userId);
        user.setId(userId);
        User editedUser = userRepository.save(user);
        log.info("User updated: {}", editedUser);
        return editedUser;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public User get(Long userId) {
        return userRepository.findUserByIdAndIsActiveTrue(userId).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id: <%d> not found", userId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAllByIsActiveTrue();
    }

    @Override
    @Transactional
    public void remove(Long idForDelete, Long userId) {
        // проеряем совпадение айди пользователя, отправившего запрос, и айди пользователя кого удаляем
        checkOwner(idForDelete, userId);
        User user = get(userId);
        //деактивируем запись в БД
        user.setIsActive(false);
        userRepository.save(user);
        log.info("User inactivated: {}", user);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public User getById(Long userId) {
        return userRepository.findByIdAndIsActiveTrue(userId).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id: <%d> not found", userId)));
    }

    private void checkDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EntityArgumentException(String.format("User with email <%s> already registered", email));
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
