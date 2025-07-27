package ru.serggge.controller;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.serggge.dto.*;

@RequestMapping("/auth")
public interface AuthOperations {

    @PostMapping("/register")
    RegisterResponse register(@RequestBody RegisterRequest request);

    @PostMapping("/login")
    LoginResponse login(@RequestBody LoginRequest request);

    @PatchMapping("/refresh")
    RefreshResponse refresh(@RequestBody RefreshRequest request);
}