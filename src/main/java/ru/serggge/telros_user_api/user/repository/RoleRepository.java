package ru.serggge.telros_user_api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.serggge.telros_user_api.user.model.RoleType;
import ru.serggge.telros_user_api.user.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleType(RoleType roleType);
}
