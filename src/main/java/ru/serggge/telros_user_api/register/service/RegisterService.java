package ru.serggge.telros_user_api.register.service;

import ru.serggge.telros_user_api.login.model.JwtToken;
import ru.serggge.telros_user_api.register.entity.Credential;

public interface RegisterService {

    JwtToken add(Credential credential);
}