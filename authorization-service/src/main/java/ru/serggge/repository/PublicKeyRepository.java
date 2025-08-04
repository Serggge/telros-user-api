package ru.serggge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.serggge.entity.PublicKeyEntity;

import java.util.Optional;

public interface PublicKeyRepository extends JpaRepository<PublicKeyEntity, Long> {

    Optional<PublicKeyEntity> findByIssuerAndIsActive(String issuer, boolean active);
}
