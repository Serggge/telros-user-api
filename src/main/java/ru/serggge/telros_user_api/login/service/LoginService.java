package ru.serggge.telros_user_api.login.service;

import ru.serggge.telros_user_api.login.model.AccessToken;

public interface LoginService {

    AccessToken getToken(String login, String password);
}
