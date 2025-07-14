package ru.serggge.telros_user_api.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.serggge.telros_user_api.controller.admin.dto.ViewAllUserDtoResponse;
import ru.serggge.telros_user_api.controller.admin.dto.ViewUserDtoResponse;

import java.util.List;

@RequestMapping("/default")
public interface AdminOperations {

    @GetMapping("/users/{id}")
    ViewUserDtoResponse view(@PathVariable("id") Long userId);

    @GetMapping("/users")
    public List<ViewAllUserDtoResponse> viewAll();
}
