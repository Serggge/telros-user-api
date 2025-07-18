package ru.serggge.telros_user_api.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.serggge.telros_user_api.handler.exception.EntityArgumentException;
import ru.serggge.telros_user_api.handler.exception.SecurityAccessException;
import ru.serggge.telros_user_api.handler.exception.UserNotFoundException;
import ru.serggge.telros_user_api.register.entity.Credential;
import ru.serggge.telros_user_api.user.model.RoleType;
import ru.serggge.telros_user_api.user.entity.User;
import ru.serggge.telros_user_api.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;


    @Override
    public void createGuest(Credential credential) {
        // создали шаблонного пользователя с уникальным email = login
        User guest = getDefaultUser(credential.getLogin());
        guest.setCredential(credential);
        userRepository.save(guest);
    }

    @Override
    @Transactional
    public User add(User user) {
        // проверяем, нет ли уже пользователя с таким же логином
        checkDuplicateEmail(user);
        // для новых пользователей устанавливаем роль User
        User createdUser = userRepository.save(user);
        log.info("User created: {}", createdUser);
        return createdUser;
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

    private User getDefaultUser(String login) {
        return User.builder()
                   .firstName("GUEST")
                   .lastName("GUEST")
                   .email(login)
                   .phoneNumber("NONE")
                   .birthday(LocalDate.now())
                   .role(roleService.getRole(RoleType.USER))
                   .isActive(false)
                   .build();
    }
}
