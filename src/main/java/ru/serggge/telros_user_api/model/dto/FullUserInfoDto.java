package ru.serggge.telros_user_api.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import ru.serggge.telros_user_api.model.RoleType;
import java.time.LocalDate;

@Data
@Builder
public class FullUserInfoDto {

    private Long id;
    private String lastName;
    private String firstName;
    private String surname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String email;
    private String phoneNumber;
    private RoleType role;
    private CredentialDto credentials;
}
