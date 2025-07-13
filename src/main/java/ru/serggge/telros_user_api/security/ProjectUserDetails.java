package ru.serggge.telros_user_api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.serggge.telros_user_api.model.entity.User;
import java.io.Serial;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class ProjectUserDetails implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().getRoleType().name());
        return List.of(grantedAuthority);
    }

    @Override
    public String getUsername() {
        return user.getCredential().getLogin();
    }

    @Override
    public String getPassword() {
        return user.getCredential().getPassword();
    }

}