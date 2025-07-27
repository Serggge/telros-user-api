package ru.serggge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.serggge.entity.Credentials;
import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credentials, Long> {

    Optional<Credentials> findByLoginIgnoreCaseAndActiveTrue(String login);
}
