package ru.serggge.telros_user_api.controller.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class UserContactDto implements Personable {

    protected String person;
    protected String email;
    protected String phoneNumber;

}
