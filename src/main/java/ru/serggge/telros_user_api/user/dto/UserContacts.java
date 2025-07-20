package ru.serggge.telros_user_api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserContacts {

    protected String person;
    protected String email;
    protected String phoneNumber;

}
