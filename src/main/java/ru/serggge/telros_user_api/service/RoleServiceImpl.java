package ru.serggge.telros_user_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.serggge.telros_user_api.model.Role;
import ru.serggge.telros_user_api.model.RoleType;
import ru.serggge.telros_user_api.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleType getRole(Role role) {
        return roleRepository.findByRole(role);
    }
}
