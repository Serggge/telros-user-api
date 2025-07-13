package ru.serggge.telros_user_api.service;

import ru.serggge.telros_user_api.model.RoleType;
import ru.serggge.telros_user_api.model.entity.Role;

public interface RoleService {

    Role getRole(RoleType role);
}
