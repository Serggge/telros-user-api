package ru.serggge.telros_user_api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record UserDto(String lastName, String firstName, String surname,
                      @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthday, String email, String phoneNumber) {

}