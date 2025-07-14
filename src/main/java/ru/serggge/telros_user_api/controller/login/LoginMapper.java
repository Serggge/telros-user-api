package ru.serggge.telros_user_api.controller.login;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.serggge.telros_user_api.controller.login.dto.RegisterTokenRequestDto;
import ru.serggge.telros_user_api.controller.login.dto.RegisterTokenResponseDto;
import ru.serggge.telros_user_api.model.dto.TokenRequest;
import ru.serggge.telros_user_api.model.dto.TokenResponse;

@Component
public class LoginMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public TokenRequest toTokenRequest(RegisterTokenRequestDto tokenRequestDto) {
        return modelMapper.map(tokenRequestDto, TokenRequest.class);
    }

    public RegisterTokenResponseDto toTokenResponseDto(TokenResponse tokenResponse) {
        return modelMapper.map(tokenResponse, RegisterTokenResponseDto.class);
    }
}
