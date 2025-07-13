package ru.serggge.telros_user_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.serggge.telros_user_api.model.dto.ContactDto;
import ru.serggge.telros_user_api.model.entity.User;
import ru.serggge.telros_user_api.model.dto.UserInfoDto;
import ru.serggge.telros_user_api.service.UserService;
import ru.serggge.telros_user_api.util.UserMapper;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDto register(@RequestBody @Valid UserInfoDto userInfoDto) {
      User createdUser = userService.add(userMapper.toUser(userInfoDto));
      return userMapper.toContactDto(createdUser);
    }

    @PatchMapping
    public ContactDto edit(@RequestBody @Valid UserInfoDto userInfoDto,
                           @RequestHeader("X-User-ID") Long userId) {
        User editedUser = userService.update(userMapper.toUser(userInfoDto), userId);
        return userMapper.toContactDto(editedUser);
    }

    @GetMapping("/{id}")
    public ContactDto view(@PathVariable("id") Long userId) {
        User user = userService.get(userId);
        return userMapper.toContactDto(user);
    }

    @GetMapping
    public List<ContactDto> viewAll() {
        List<User> allUsers = userService.getAll();
        return userMapper.toContactDto(allUsers);
    }

    @DeleteMapping("/{id}")
    public void erase(@PathVariable("id") Long idForDelete,
                      @RequestHeader("X-User-ID") Long userId) {
        userService.remove(idForDelete, userId);
    }
}