package ru.serggge.telros_user_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class RoleType {

    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private Role role;
}
