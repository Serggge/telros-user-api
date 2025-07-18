package ru.serggge.telros_user_api.login.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.serggge.telros_user_api.login.dto.RegisterRequestDto;
import ru.serggge.telros_user_api.login.dto.RegisterResponseDto;
import ru.serggge.telros_user_api.login.model.JwtToken;
import ru.serggge.telros_user_api.login.util.LoginMapper;
import ru.serggge.telros_user_api.login.service.LoginService;
import ru.serggge.telros_user_api.register.entity.Credential;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController implements LoginOperations {

    private final LoginService loginService;
    private final LoginMapper loginMapper;

    /**
     *
     * @param tokenRequestDto accepts Json with user login and password
     * @return returns JWT access token as Json
     */
    @Override
    public RegisterResponseDto registerUser(RegisterRequestDto tokenRequestDto) {
        Credential credential = loginMapper.toCredential(tokenRequestDto);
        JwtToken jwtToken = loginService.getToken(credential.getLogin(), credential.getPassword());
        return loginMapper.toTokenResponseDto(jwtToken);
    }

}