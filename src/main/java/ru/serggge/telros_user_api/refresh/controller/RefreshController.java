package ru.serggge.telros_user_api.refresh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.serggge.telros_user_api.login.model.AccessToken;
import ru.serggge.telros_user_api.refresh.dto.RefreshTokenRequest;
import ru.serggge.telros_user_api.refresh.dto.RefreshTokenResponse;
import ru.serggge.telros_user_api.refresh.entity.RefreshToken;
import ru.serggge.telros_user_api.refresh.service.RefreshService;
import ru.serggge.telros_user_api.refresh.util.RefreshMapper;

@RestController
@RequiredArgsConstructor
public class RefreshController implements RefreshOperations {

    private final RefreshService refreshService;
    private final RefreshMapper refreshMapper;

    @Override
    public RefreshTokenResponse refresh(RefreshTokenRequest request, String login) {
        // по полученному рефреш токену создаём новый рефреш и аксес токен
        RefreshToken refreshToken = refreshService.get(request.getRefreshToken(), login);
        AccessToken accessToken =  refreshService.createAccessToken(login);
        return refreshMapper.toTokenResponse(accessToken, refreshToken);
    }
}