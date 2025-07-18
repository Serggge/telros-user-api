package ru.serggge.telros_user_api.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.serggge.telros_user_api.register.entity.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Long> {
}
