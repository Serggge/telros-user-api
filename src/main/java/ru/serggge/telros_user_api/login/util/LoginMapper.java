package ru.serggge.telros_user_api.login.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.serggge.telros_user_api.login.dto.LoginRequest;
import ru.serggge.telros_user_api.login.dto.LoginResponse;
import ru.serggge.telros_user_api.login.model.AccessToken;
import ru.serggge.telros_user_api.register.entity.Credential;

@Component
public class LoginMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Credential toCredential(LoginRequest dto) {
        return modelMapper.map(dto, Credential.class);
    }

    public LoginResponse toTokenResponseDto(AccessToken accessToken) {
        return new LoginResponse(accessToken.token());
    }
}
