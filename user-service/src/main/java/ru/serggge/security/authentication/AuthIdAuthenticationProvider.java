package ru.serggge.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class AuthIdAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(@Autowired Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String authId = userDetails.getUsername();
        if (Objects.nonNull(authId) && Objects.equals(authId, authentication.getPrincipal())) {
            return new AuthIdAuthenticationToken(userDetails);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AuthIdAuthenticationToken.class.isAssignableFrom(authentication);
    }
}