package ru.serggge.telros_user_api.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDto {
    private String person;
    private String email;
    private String phoneNumber;
}
