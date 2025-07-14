package ru.serggge.telros_user_api.controller.admin.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.serggge.telros_user_api.controller.login.dto.RoleSettable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewUserDtoResponse implements RoleSettable {
    private Long id;
    private String lastName;
    private String firstName;
    private String surname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String email;
    private String phoneNumber;
    private String role;
}
