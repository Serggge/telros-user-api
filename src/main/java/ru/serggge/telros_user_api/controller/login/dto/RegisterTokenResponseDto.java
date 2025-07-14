package ru.serggge.telros_user_api.controller.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterTokenResponseDto {
    private String token;
}
