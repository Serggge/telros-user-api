package ru.serggge.telros_user_api.login.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.serggge.telros_user_api.login.dto.RegisterRequestDto;
import ru.serggge.telros_user_api.login.dto.RegisterResponseDto;
import ru.serggge.telros_user_api.login.model.AccessToken;
import ru.serggge.telros_user_api.register.entity.Credential;

@Component
public class LoginMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Credential toCredential(RegisterRequestDto dto) {
        return modelMapper.map(dto, Credential.class);
    }

    public RegisterResponseDto toTokenResponseDto(AccessToken accessToken) {
        return modelMapper.map(accessToken, RegisterResponseDto.class);
    }
}
