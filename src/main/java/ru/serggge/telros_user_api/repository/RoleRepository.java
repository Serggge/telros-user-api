package ru.serggge.telros_user_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.serggge.telros_user_api.model.Role;
import ru.serggge.telros_user_api.model.RoleType;

public interface RoleRepository extends JpaRepository<RoleType, Long> {

    public RoleType findByRole(Role role);
}
