package ru.serggge.telros_user_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.serggge.telros_user_api.model.dto.TokenRequest;
import ru.serggge.telros_user_api.model.dto.TokenResponse;
import ru.serggge.telros_user_api.security.JwtHelper;
import ru.serggge.telros_user_api.service.LoginService;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtHelper jwtHelper;

    @Override
    public TokenResponse getToken(TokenRequest tokenRequest) {
        String login = tokenRequest.getLogin();
        String password = tokenRequest.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        String jwtToken = jwtHelper.createJwtToken(userDetails.getUsername(), userDetails.getPassword());
        return new TokenResponse(jwtToken);
    }
}
