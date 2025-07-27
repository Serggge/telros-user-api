package ru.serggge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.serggge.entity.Credentials;
import ru.serggge.model.AuthUserDetails;
import ru.serggge.repository.CredentialRepository;

@Component
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final CredentialRepository credentialRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Credentials credentials = credentialRepository.findByLoginIgnoreCaseAndActiveTrue(login)
                                                      .orElseThrow(() ->
                                                              new RuntimeException("Login is not registered"));
        return new AuthUserDetails(credentials);
    }
}