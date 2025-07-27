package ru.serggge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.serggge.entity.RefreshToken;

import java.util.Optional;

public interface RefreshRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByCredentialsLogin(String login);

    Optional<RefreshToken> findByToken(String token);
}
