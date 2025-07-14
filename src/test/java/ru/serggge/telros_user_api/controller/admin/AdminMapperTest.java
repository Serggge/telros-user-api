package ru.serggge.telros_user_api.controller.admin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.serggge.telros_user_api.controller.admin.dto.ViewAllUserDtoResponse;
import ru.serggge.telros_user_api.controller.admin.dto.ViewUserDtoResponse;
import ru.serggge.telros_user_api.model.RoleType;
import ru.serggge.telros_user_api.model.entity.Credential;
import ru.serggge.telros_user_api.model.entity.Role;
import ru.serggge.telros_user_api.model.entity.User;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdminMapperTest {

    AdminMapper adminMapper = new AdminMapper();
    User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Ivanov")
                .surname("Ivanovich")
                .birthday(LocalDate.now())
                .email("ivan@mail.ru")
                .phoneNumber("111-111-111")
                .role(new Role(1L, RoleType.USER))
                .credential(new Credential(1L, "login", "password"))
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Map to UserDTO view endpoint")
    void toViewUserDtoResponse() {
        ViewUserDtoResponse dtoResponse = adminMapper.toViewUserDtoResponse(user);
        assertNotNull(dtoResponse);

        assertEquals(user.getId(), dtoResponse.getId());
        assertEquals(user.getFirstName(), dtoResponse.getFirstName());
        assertEquals(user.getLastName(), dtoResponse.getLastName());
        assertEquals(user.getSurname(), dtoResponse.getSurname());
        assertEquals(user.getBirthday(), dtoResponse.getBirthday());
        assertEquals(user.getEmail(), dtoResponse.getEmail());
        assertEquals(user.getPhoneNumber(), dtoResponse.getPhoneNumber());
        assertEquals(user.getRole().getRoleType().name(), dtoResponse.getRole());
    }

    @Test
    @DisplayName("Map to UserDto viewAll endpoint")
    void toViewAllUserDtoResponse() {
        List<ViewAllUserDtoResponse> allUsers = adminMapper.toViewAllUserDtoResponse(List.of(user));

        assertNotNull(allUsers);
        assertEquals(1, allUsers.size());

        ViewAllUserDtoResponse dtoResponse = allUsers.get(0);
        assertNotNull(dtoResponse);
        assertEquals(user.getId(), dtoResponse.getId());

        assertEquals(user.getFirstName(), dtoResponse.getFirstName());
        assertEquals(user.getLastName(), dtoResponse.getLastName());
        assertEquals(user.getSurname(), dtoResponse.getSurname());
        assertEquals(user.getBirthday(), dtoResponse.getBirthday());
        assertEquals(user.getEmail(), dtoResponse.getEmail());
        assertEquals(user.getPhoneNumber(), dtoResponse.getPhoneNumber());
        assertEquals(user.getRole().getRoleType().name(), dtoResponse.getRole());
    }

}