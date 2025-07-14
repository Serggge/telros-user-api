package ru.serggge.telros_user_api.controller.user.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RegisterResponse extends UserContactDto {

    public RegisterResponse(String person, String email, String phoneNumber) {
        super(person, email, phoneNumber);
    }
}
