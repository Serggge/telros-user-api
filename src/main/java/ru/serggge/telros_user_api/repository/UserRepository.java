package ru.serggge.telros_user_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.serggge.telros_user_api.model.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT count (u) > 0 " +
            "FROM User u " +
            "WHERE u.email LIKE :email")
    boolean existsByEmail(String email);

    //Optional<User> findUserByCredential_Login(String login);

    @Query("SELECT u " +
            "FROM User u " +
            "JOIN u.credential c " +
            "WHERE c.login LIKE :login")
    Optional<User> findByLogin(String login);

    @Query("SELECT u " +
            "FROM User u " +
            "CROSS JOIN Credential c " +
            "CROSS JOIN Role r " +
            "WHERE u.id = :userId")
    Optional<User> findByIdWithFullInfo(Long userId);

    @Query("SELECT u " +
            "FROM User u ")
    List<User> findAllWithFullInfo();

}
