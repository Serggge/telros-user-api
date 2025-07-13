package ru.serggge.telros_user_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.serggge.telros_user_api.model.entity.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
}
