package ru.serggge.telros_user_api.service;

import ru.serggge.telros_user_api.model.Role;
import ru.serggge.telros_user_api.model.RoleType;

public interface RoleService {

    RoleType getRole(Role role);
}
