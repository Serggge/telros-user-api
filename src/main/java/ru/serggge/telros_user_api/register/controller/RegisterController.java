package ru.serggge.telros_user_api.register.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.serggge.telros_user_api.login.model.AccessToken;
import ru.serggge.telros_user_api.refresh.entity.RefreshToken;
import ru.serggge.telros_user_api.refresh.service.RefreshService;
import ru.serggge.telros_user_api.register.dto.RegisterRequest;
import ru.serggge.telros_user_api.register.dto.RegisterResponse;
import ru.serggge.telros_user_api.register.entity.Credential;
import ru.serggge.telros_user_api.register.service.RegisterService;
import ru.serggge.telros_user_api.register.util.RegisterMapper;

@RestController
@RequiredArgsConstructor
public class RegisterController implements RegisterOperations {

    private final RegisterService registerService;
    private final RefreshService refreshService;
    private final RegisterMapper registerMapper;

    @Override
    public RegisterResponse register(RegisterRequest dto) {
        Credential credentials = registerMapper.toCredentials(dto);
        AccessToken accessToken = registerService.createAccessToken(credentials);
        RefreshToken refreshToken = refreshService.createRefreshToken(credentials.getLogin());
        return registerMapper.toRegisterResponse(accessToken, refreshToken);
    }
}
