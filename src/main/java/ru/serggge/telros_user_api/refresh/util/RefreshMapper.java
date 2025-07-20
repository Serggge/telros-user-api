package ru.serggge.telros_user_api.refresh.util;

import org.springframework.stereotype.Component;
import ru.serggge.telros_user_api.login.model.AccessToken;
import ru.serggge.telros_user_api.refresh.dto.RefreshTokenResponse;
import ru.serggge.telros_user_api.refresh.entity.RefreshToken;

@Component
public class RefreshMapper {


    public RefreshTokenResponse toTokenResponse(AccessToken accessToken, RefreshToken refreshToken) {
        return new RefreshTokenResponse(accessToken.token(), refreshToken.getToken());
    }
}
