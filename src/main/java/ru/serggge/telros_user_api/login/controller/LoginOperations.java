package ru.serggge.telros_user_api.login.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.serggge.telros_user_api.login.dto.LoginRequest;
import ru.serggge.telros_user_api.login.dto.LoginResponse;

@RestController("/default")
public interface LoginOperations {

    @PostMapping
    LoginResponse registerUser(@RequestBody LoginRequest tokenRequest);
}
