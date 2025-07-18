package ru.serggge.telros_user_api.register.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.serggge.telros_user_api.login.model.JwtToken;
import ru.serggge.telros_user_api.register.dto.RegisterRequest;
import ru.serggge.telros_user_api.register.dto.RegisterResponse;
import ru.serggge.telros_user_api.register.entity.Credential;

@Component
public class RegisterMapper {

    ModelMapper modelMapper = new ModelMapper();

    public Credential toCredentials(RegisterRequest dto) {
        return modelMapper.map(dto, Credential.class);
    }

    public RegisterResponse toRegisterResponse(JwtToken jwtToken) {
        return new RegisterResponse(jwtToken.token());
    }
}
