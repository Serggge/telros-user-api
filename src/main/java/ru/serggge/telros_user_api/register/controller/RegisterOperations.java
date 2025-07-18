package ru.serggge.telros_user_api.register.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.serggge.telros_user_api.register.dto.RegisterRequest;
import ru.serggge.telros_user_api.register.dto.RegisterResponse;

@RequestMapping("/register")
public interface RegisterOperations {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RegisterResponse register(@RequestBody @Valid RegisterRequest request);

}
