package ru.serggge.telros_user_api.controller.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterTokenRequestDto {

    private String login;
    private String password;
}
