package ru.serggge.dto;

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
