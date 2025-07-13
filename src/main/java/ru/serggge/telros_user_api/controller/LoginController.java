package ru.serggge.telros_user_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.serggge.telros_user_api.model.dto.TokenRequest;
import ru.serggge.telros_user_api.model.dto.TokenResponse;
import ru.serggge.telros_user_api.service.LoginService;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public TokenResponse registerUser(@RequestBody TokenRequest tokenRequest) {
        return loginService.getToken(tokenRequest);
    }

}
