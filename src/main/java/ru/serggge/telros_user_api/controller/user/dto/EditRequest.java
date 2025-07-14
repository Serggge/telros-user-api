package ru.serggge.telros_user_api.controller.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditRequest {

    private String lastName;
    private String firstName;
    private String surname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String email;
    private String phoneNumber;
}
