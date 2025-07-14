package ru.serggge.telros_user_api.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.serggge.telros_user_api.model.RoleType;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleType roleType;
}
