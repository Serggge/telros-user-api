package ru.serggge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credential_id", referencedColumnName = "id")
    private Credentials credentials;
    @Column(name = "token")
    private String token;
    @Column(name = "expired_time")
    Instant expiredTime;
    @Column(name = "active")
    boolean isActive;
}