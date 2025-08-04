package ru.serggge.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.serggge.security.entity.UserAuthorization;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuthorization, Long> {

    Optional<UserAuthorization> findByAuthIdAndIsActiveTrue(Long authId);
}
