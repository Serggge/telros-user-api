package ru.serggge.telros_user_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.serggge.telros_user_api.model.dto.FullUserInfoDto;
import ru.serggge.telros_user_api.model.entity.User;
import ru.serggge.telros_user_api.service.UserService;
import ru.serggge.telros_user_api.util.UserMapper;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/users/{id}")
    public FullUserInfoDto view(@PathVariable("id") Long userId) {
        User user = userService.getByIdEager(userId);
        return userMapper.toFullUserInfo(user);
    }

    @GetMapping("/users")
    public List<FullUserInfoDto> viewAll() {
        List<User> allUsers = userService.getAllEager();
        return userMapper.toFullUserInfo(allUsers);
    }

}
