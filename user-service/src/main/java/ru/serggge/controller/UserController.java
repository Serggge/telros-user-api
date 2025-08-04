package ru.serggge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.serggge.dto.CreateRequest;
import ru.serggge.dto.EditRequest;
import ru.serggge.dto.EditResponse;
import ru.serggge.dto.ViewResponse;
import ru.serggge.util.UserMapper;
import ru.serggge.model.UserInfo;
import ru.serggge.entity.User;
import ru.serggge.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements UserOperations {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    @PostMapping
    public UserInfo create(CreateRequest dto) {
        User user = userMapper.createRequestToUser(dto);
        user = userService.add(user);
        return userMapper.toRegisterDto(user);
    }

    @Override
    public EditResponse edit(EditRequest request, Long userId) {
        User user = userMapper.editRequestToUser(request);
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