package ru.serggge.telros_user_api.register.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {

    private String accessToken;
    private String refreshToken;
}
