package ru.serggge.telros_user_api.controller.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.serggge.telros_user_api.user.model.UserInfo;
import ru.serggge.telros_user_api.user.model.RoleType;
import ru.serggge.telros_user_api.register.entity.Credential;
import ru.serggge.telros_user_api.user.entity.Role;
import ru.serggge.telros_user_api.user.entity.User;
import ru.serggge.telros_user_api.user.util.UserMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    UserMapper userMapper = new UserMapper();
    User user;
    static String firstName = "Ivan";
    static String lastName = "Ivanov";
    static String surname = "Ivanovich";
    static String email = "ivan@mail.ru";
    static String phoneNumber = "123456789";

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .firstName(firstName)
                .lastName(lastName)
                .surname(surname)
                .birthday(LocalDate.now())
                .email(email)
                .phoneNumber(phoneNumber)
                .role(new Role(1L, RoleType.USER))
                .credential(new Credential(1L, "login", "password"))
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createDtoToUser() {

    }

    @Test
    void editToUser() {
    }

    @Test
    void toRegisterDto() {
        String fullName = lastName + " " + firstName + " " + surname;
        UserInfo expectedResponse = new UserInfo(fullName, email, phoneNumber);

        UserInfo mappedDto = userMapper.toRegisterDto(user);

        assertNotNull(mappedDto);
        assertEquals(expectedResponse, mappedDto);
    }

    @Test
    void toEditDto() {
    }

    @Test
    void toViewDto() {
    }

    @Test
    void toViewDtoList() {
    }
}