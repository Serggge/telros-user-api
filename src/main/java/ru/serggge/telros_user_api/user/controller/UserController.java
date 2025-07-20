package ru.serggge.telros_user_api.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.serggge.telros_user_api.user.dto.CreateRequest;
import ru.serggge.telros_user_api.user.dto.EditRequest;
import ru.serggge.telros_user_api.user.dto.EditResponse;
import ru.serggge.telros_user_api.user.dto.ViewResponse;
import ru.serggge.telros_user_api.user.util.UserMapper;
import ru.serggge.telros_user_api.user.model.UserInfo;
import ru.serggge.telros_user_api.user.entity.User;
import ru.serggge.telros_user_api.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements UserOperations {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserInfo create(CreateRequest dto, String login) {
        User user = userMapper.createDtoToUser(dto);
        user = userService.add(user, login);
        return userMapper.toRegisterDto(user);
    }

    @Override
    public EditResponse edit(EditRequest request, Long userId) {
        User user = userMapper.editToUser(request);
        user = userService.update(user, userId);
        return userMapper.toEditDto(user);
    }

    @Override
    public ViewResponse view(Long userId) {
        User user = userService.get(userId);
        return userMapper.toViewDto(user);
    }

    @Override
    public List<ViewResponse> viewAll() {
        List<User> allUsers = userService.getAll();
        return userMapper.toViewDtoList(allUsers);
    }

    @Override
    public void erase(Long idForDelete, Long userId) {
        userService.remove(idForDelete, userId);
    }
}