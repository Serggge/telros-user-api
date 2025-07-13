package ru.serggge.telros_user_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.serggge.telros_user_api.model.RoleType;
import ru.serggge.telros_user_api.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleType(RoleType roleType);
}
