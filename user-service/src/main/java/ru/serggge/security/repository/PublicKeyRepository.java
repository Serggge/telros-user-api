package ru.serggge.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.serggge.security.entity.PublicKeyEntity;

import java.util.Optional;

public interface PublicKeyRepository extends JpaRepository<PublicKeyEntity, Long> {

    Optional<PublicKeyEntity> findByKeyIdentifierAndIsActiveTrue(String keyId);
}
