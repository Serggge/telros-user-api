package ru.serggge.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.serggge.security.authentication.AuthUserDetails;
import ru.serggge.security.entity.UserAuthorization;
import ru.serggge.security.repository.UserAuthRepository;

@Component
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Long authId = Long.parseLong(username);
        return loadUserByUsername(authId);
    }

    private UserDetails loadUserByUsername(Long authId) throws UsernameNotFoundException {
        UserAuthorization userAuthorization = userAuthRepository.findByAuthIdAndIsActiveTrue(authId)
                                                                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new AuthUserDetails(userAuthorization);
    }
}