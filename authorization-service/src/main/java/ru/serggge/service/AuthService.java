package ru.serggge.service;

import ru.serggge.entity.RefreshToken;
import ru.serggge.model.AccessToken;
import ru.serggge.entity.Credentials;

public interface AuthService {

    AccessToken register(Credentials credentials);

    AccessToken login(Credentials credentials);

    RefreshToken createRefreshToken(Credentials credentials);

    RefreshToken updateRefreshToken(String oldToken);

    RefreshToken getRefreshToken(Credentials credentials);

    AccessToken updateAccessToken(Credentials credentials);
}
