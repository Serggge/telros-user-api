package ru.serggge.telros_user_api.controller.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.serggge.telros_user_api.controller.login.dto.RegisterTokenRequestDto;
import ru.serggge.telros_user_api.controller.login.dto.RegisterTokenResponseDto;

@RestController("/default")
public interface LoginOperations {

    @PostMapping
    RegisterTokenResponseDto registerUser(@RequestBody RegisterTokenRequestDto tokenRequest);
}
