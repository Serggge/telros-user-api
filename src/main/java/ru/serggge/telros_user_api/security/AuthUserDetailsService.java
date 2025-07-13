package ru.serggge.telros_user_api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.serggge.telros_user_api.handler.exception.UserNotFoundException;
import ru.serggge.telros_user_api.model.entity.User;
import ru.serggge.telros_user_api.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login)
                                            .orElseThrow(() -> new UserNotFoundException("Login not registered: " + login));
        return new ProjectUserDetails(user);
    }
}
