package ru.serggge.telros_user_api.user.model;

import lombok.NoArgsConstructor;
import ru.serggge.telros_user_api.user.dto.UserContactDto;

@NoArgsConstructor
public class UserInfo extends UserContactDto {

    public UserInfo(String person, String email, String phoneNumber) {
        super(person, email, phoneNumber);
    }
}
