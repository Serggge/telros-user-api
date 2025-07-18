package ru.serggge.telros_user_api.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.serggge.telros_user_api.user.model.RoleType;
import ru.serggge.telros_user_api.user.entity.Role;
import ru.serggge.telros_user_api.user.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Role getRole(RoleType roleType) {
        return roleRepository.findByRoleType(roleType);
    }
}
