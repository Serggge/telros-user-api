package ru.serggge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.serggge.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT count (u) > 0 " +
            "FROM User u " +
            "WHERE u.email LIKE :email " +
            "AND u.isActive=true")
    boolean existsByEmail(String email);

    Optional<User> findByIdAndIsActiveTrue(Long userId);

    Optional<User> findUserByIdAndIsActiveTrue(Long userId);

    List<User> findAllByIsActiveTrue();

}