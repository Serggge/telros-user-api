package ru.serggge.telros_user_api.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.serggge.telros_user_api.register.entity.Credential;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    Optional<Credential> findByLoginIgnoreCase(String login);
}
