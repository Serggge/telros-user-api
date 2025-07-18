package ru.serggge.telros_user_api.user.service;

import ru.serggge.telros_user_api.user.model.RoleType;
import ru.serggge.telros_user_api.user.entity.Role;

public interface RoleService {

    Role getRole(RoleType role);
}
