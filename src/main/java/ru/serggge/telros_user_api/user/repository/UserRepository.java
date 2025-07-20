package ru.serggge.telros_user_api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.serggge.telros_user_api.register.repository.UserId;
import ru.serggge.telros_user_api.user.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT count (u) > 0 " +
            "FROM User u " +
            "WHERE u.email LIKE :email " +
            "AND u.isActive=true")
    boolean existsByEmail(String email);

    @Query("SELECT u " +
            "FROM User u " +
            "JOIN u.credential c " +
            "WHERE c.login LIKE :login " +
            "AND u.isActive=true")
    Optional<User> findByLogin(String login);

    @Query("SELECT u " +
            "FROM User u " +
            "LEFT JOIN u.credential c " +
            "LEFT JOIN u.role r " +
            "WHERE u.id = :userId " +
            "AND u.isActive=true")
    Optional<User> findByIdWithFullInfo(Long userId);

    @Query("SELECT u " +
            "FROM User u " +
            "LEFT JOIN u.credential c " +
            "LEFT JOIN u.role r " +
            "WHERE u.isActive=true")
    List<User> findAllWithFullInfo();

    Optional<User> findUserByIdAndIsActiveTrue(Long userId);

    List<User> findAllByIsActiveTrue();

    Optional<User> findUserByCredentialLoginAndIsActiveTrue(String credentialLogin);

    Optional<UserId> findByCredentialLogin(String login);

}