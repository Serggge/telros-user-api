package ru.serggge.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.serggge.dto.*;
import ru.serggge.entity.Credentials;
import ru.serggge.entity.RefreshToken;
import ru.serggge.model.AccessToken;

@Component
public class AuthMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Credentials toCredentials(RegisterRequest request) {
        return modelMapper.map(request, Credentials.class);
    }

    public Credentials toCredentials(LoginRequest request) {
        return modelMapper.map(request, Credentials.class);
    }

    public RegisterResponse toRegisterResponse(AccessToken accessToken, RefreshToken refreshToken) {
        return new RegisterResponse(accessToken.getToken(), refreshToken.getToken());
    }

    public LoginResponse toLoginResponse(AccessToken accessToken, RefreshToken refreshToken) {
        return new LoginResponse(accessToken.getToken(), refreshToken.getToken());
    }

    public RefreshResponse toRefreshResponse(AccessToken accessToken, RefreshToken refreshToken) {
        return new RefreshResponse(accessToken.getToken(), refreshToken.getToken());
    }
}