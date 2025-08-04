package ru.serggge.security.authentication;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.serggge.security.entity.UserAuthorization;
import java.util.Collection;
import java.util.List;

@Data
@RequiredArgsConstructor
public class AuthUserDetails implements UserDetails {

    private final UserAuthorization userAuthorization;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return Long.toString(userAuthorization.getAuthId());
    }
}