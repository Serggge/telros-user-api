package ru.serggge.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.serggge.entity.User;

@Entity
@Table(name = "user_authorization")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthorization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "auth_id")
    private Long authId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "active")
    private boolean isActive;
}