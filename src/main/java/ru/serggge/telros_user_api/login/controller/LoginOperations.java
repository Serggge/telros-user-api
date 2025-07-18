package ru.serggge.telros_user_api.login.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.serggge.telros_user_api.login.dto.RegisterRequestDto;
import ru.serggge.telros_user_api.login.dto.RegisterResponseDto;

@RestController("/default")
public interface LoginOperations {

    @PostMapping
    RegisterResponseDto registerUser(@RequestBody RegisterRequestDto tokenRequest);
}
