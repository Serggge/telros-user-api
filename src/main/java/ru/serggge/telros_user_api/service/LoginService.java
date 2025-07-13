package ru.serggge.telros_user_api.service;

import ru.serggge.telros_user_api.model.dto.TokenRequest;
import ru.serggge.telros_user_api.model.dto.TokenResponse;

public interface LoginService {

    TokenResponse getToken(TokenRequest tokenRequest);
}
