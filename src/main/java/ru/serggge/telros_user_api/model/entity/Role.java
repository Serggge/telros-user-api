package ru.serggge.telros_user_api.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.serggge.telros_user_api.model.RoleType;

@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleType roleType;
}
