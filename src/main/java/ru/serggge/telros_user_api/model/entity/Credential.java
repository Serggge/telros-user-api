package ru.serggge.telros_user_api.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "credentials")
@Data
public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
}
