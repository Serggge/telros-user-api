package ru.serggge.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.serggge.entity.Credentials;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class AuthUserDetails implements UserDetails {

    private final Credentials credentials;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return credentials.getPassword();
    }

    @Override
    public String getUsername() {
        return credentials.getLogin();
    }
}
