package ru.serggge.telros_user_api.login.service;

import ru.serggge.telros_user_api.login.model.JwtToken;

public interface LoginService {

    JwtToken getToken(String login, String password);
}
