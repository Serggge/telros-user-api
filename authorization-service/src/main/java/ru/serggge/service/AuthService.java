package ru.serggge.service;

import ru.serggge.model.RefreshToken;
import ru.serggge.model.AccessToken;
import ru.serggge.entity.Credentials;

public interface AuthService {

    AccessToken register(Credentials credentials);

    AccessToken login(Credentials credentials);

    RefreshToken createRefreshToken(Long userId);

    RefreshToken updateRefreshToken(String oldToken);

    RefreshToken getRefreshToken(Long userId);

    AccessToken updateAccessToken(Long userId);
}