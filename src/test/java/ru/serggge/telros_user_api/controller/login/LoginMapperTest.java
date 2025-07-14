package ru.serggge.telros_user_api.controller.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.serggge.telros_user_api.controller.login.dto.RegisterTokenRequestDto;
import ru.serggge.telros_user_api.controller.login.dto.RegisterTokenResponseDto;
import ru.serggge.telros_user_api.model.dto.TokenRequest;
import ru.serggge.telros_user_api.model.dto.TokenResponse;
import static org.junit.jupiter.api.Assertions.*;

class LoginMapperTest {

    LoginMapper loginMapper = new LoginMapper();

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {


    }

    @Test
    @DisplayName("Map to token")
    void mapToTokenRequest() {
        TokenRequest tokenRequest = new TokenRequest("login", "password");
        RegisterTokenRequestDto dto = new RegisterTokenRequestDto("login", "password");

        TokenRequest mappedRequest = loginMapper.toTokenRequest(dto);

        assertNotNull(mappedRequest);
        assertEquals(tokenRequest, mappedRequest);
    }

    @Test
    @DisplayName("Map to dto")
    void mapToDto() {
        TokenResponse tokenResponse = new TokenResponse("token");
        RegisterTokenResponseDto dto = new RegisterTokenResponseDto("token");

        RegisterTokenResponseDto mappedDto = loginMapper.toTokenResponseDto(tokenResponse);

        assertNotNull(mappedDto);
        assertEquals(dto, mappedDto);
    }


}