package ru.serggge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "public_keys")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "key_identifier")
    private String keyIdentifier;
    @Column(name = "public_key")
    private String publicKey;
    @Column(name = "issuer")
    private String issuer;
    @Column(name = "active")
    private boolean isActive;
}
