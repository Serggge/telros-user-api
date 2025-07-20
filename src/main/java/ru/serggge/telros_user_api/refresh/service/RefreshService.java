package ru.serggge.telros_user_api.refresh.service;

import ru.serggge.telros_user_api.login.model.AccessToken;
import ru.serggge.telros_user_api.refresh.entity.RefreshToken;

public interface RefreshService {

    RefreshToken get(String token, String login);

    RefreshToken createRefreshToken(String login);

    AccessToken createAccessToken(String login);
}
