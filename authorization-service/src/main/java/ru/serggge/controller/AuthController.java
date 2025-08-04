package ru.serggge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.serggge.dto.*;
import ru.serggge.entity.Credentials;
import ru.serggge.model.RefreshToken;
import ru.serggge.model.AccessToken;
import ru.serggge.service.AuthService;
import ru.serggge.util.AuthMapper;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthOperations {

    private final AuthService authService;
    private final AuthMapper authMapper;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        Credentials credentials = authMapper.toCredentials(request);
        AccessToken accessToken = authService.register(credentials);
        RefreshToken refreshToken = authService.createRefreshToken(credentials.getId());
        return authMapper.toRegisterResponse(accessToken, refreshToken);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Credentials credentials = authMapper.toCredentials(request);
        AccessToken accessToken = authService.login(credentials);
        RefreshToken refreshToken = authService.getRefreshToken(credentials.getId());
        return authMapper.toLoginResponse(accessToken, refreshToken);
    }

    @Override
    public RefreshResponse refresh(RefreshRequest request) {
        RefreshToken refreshToken = authService.updateRefreshToken(request.getRefreshToken());
        AccessToken accessToken = authService.updateAccessToken(refreshToken.getUserId());
        return authMapper.toRefreshResponse(accessToken, refreshToken);
    }
}