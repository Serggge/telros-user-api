package ru.serggge.telros_user_api.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.serggge.telros_user_api.admin.dto.ViewAllUserDtoResponse;
import ru.serggge.telros_user_api.admin.dto.ViewUserDtoResponse;

import java.util.List;

@RequestMapping("/admin")
public interface AdminOperations {

    @GetMapping("/users/{id}")
    ViewUserDtoResponse view(@PathVariable("id") Long userId);

    @GetMapping("/users")
    List<ViewAllUserDtoResponse> viewAll();
}
