package ru.serggge.telros_user_api.refresh.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.serggge.telros_user_api.refresh.dto.RefreshTokenRequest;
import ru.serggge.telros_user_api.refresh.dto.RefreshTokenResponse;

@RequestMapping("/refresh")
public interface RefreshOperations {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RefreshTokenResponse refresh(@RequestBody @Valid RefreshTokenRequest request,
                                 @RequestHeader("X-User-Login") String login);

}
