package ru.serggge.telros_user_api.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.serggge.telros_user_api.controller.user.dto.*;
import ru.serggge.telros_user_api.model.entity.User;
import ru.serggge.telros_user_api.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements UserOperations {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public RegisterResponse register(RegisterRequest dto) {
        User user = userMapper.registerToUser(dto);
        user = userService.add(user);
        return userMapper.toRegisterDto(user);
    }

    @Override
    public EditResponse edit(EditRequest dto, Long userId) {
        User user = userMapper.editToUser(dto);
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