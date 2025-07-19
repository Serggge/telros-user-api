package ru.serggge.telros_user_api.refresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.serggge.telros_user_api.refresh.entity.RefreshToken;

import java.util.Optional;

public interface RefreshRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByUserCredentialLoginAndToken(String login, String token);
}
