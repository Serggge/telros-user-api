package ru.serggge.telros_user_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.serggge.telros_user_api.model.dto.TokenRequest;
import ru.serggge.telros_user_api.model.dto.TokenResponse;
import ru.serggge.telros_user_api.security.JwtHelper;
import ru.serggge.telros_user_api.service.LoginService;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager = null;
    private final JwtHelper jwtHelper;

    @Override
    public TokenResponse getToken(TokenRequest tokenRequest) {
        String login = tokenRequest.login();
        String password = tokenRequest.password();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        String jwtToken = jwtHelper.createJwtToken(login, password);
        return new TokenResponse(jwtToken);
    }
}
