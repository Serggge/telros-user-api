package ru.serggge.telros_user_api.controller.login;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.serggge.telros_user_api.controller.login.dto.RegisterTokenRequestDto;
import ru.serggge.telros_user_api.controller.login.dto.RegisterTokenResponseDto;
import ru.serggge.telros_user_api.model.dto.TokenRequest;
import ru.serggge.telros_user_api.model.dto.TokenResponse;
import ru.serggge.telros_user_api.service.LoginService;

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
    public RegisterTokenResponseDto registerUser(RegisterTokenRequestDto tokenRequestDto) {
        TokenRequest tokenRequest = loginMapper.toTokenRequest(tokenRequestDto);
        TokenResponse tokenResponse = loginService.getToken(tokenRequest);
        return loginMapper.toTokenResponseDto(tokenResponse);
    }

}