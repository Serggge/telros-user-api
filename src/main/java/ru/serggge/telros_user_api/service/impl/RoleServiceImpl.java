package ru.serggge.telros_user_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.serggge.telros_user_api.model.RoleType;
import ru.serggge.telros_user_api.model.entity.Role;
import ru.serggge.telros_user_api.repository.RoleRepository;
import ru.serggge.telros_user_api.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRole(RoleType roleType) {
        return roleRepository.findByRoleType(roleType);
    }
}
