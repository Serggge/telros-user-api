package ru.serggge.telros_user_api.register.service;

import ru.serggge.telros_user_api.login.model.AccessToken;
import ru.serggge.telros_user_api.register.entity.Credential;

public interface RegisterService {

    AccessToken createAccessToken(Credential credential);

    AccessToken generateNew(String login);

}