package ru.serggge.telros_user_api.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserInfoDto {

    @NotBlank
    String lastName;
    @NotBlank
    String firstName;
    String surname;
    @NotNull
    @PastOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate birthday;
    @Email
    @NotEmpty
    String email;
    @NotEmpty
    String phoneNumber;

}