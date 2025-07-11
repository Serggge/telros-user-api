package ru.serggge.telros_user_api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.serggge.telros_user_api.model.ContactDto;
import ru.serggge.telros_user_api.model.User;
import ru.serggge.telros_user_api.model.UserDto;
import ru.serggge.telros_user_api.service.UserService;
import ru.serggge.telros_user_api.util.UserMapper;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDto register(@RequestBody UserDto userDto) {
      User createdUser = userService.add(userMapper.toUser(userDto));
      return userMapper.toContactDto(createdUser);
    }

    @PatchMapping
    public ContactDto edit(@RequestBody UserDto userDto,
                           @RequestHeader("X-User-ID") Long userId) {
        User editedUser = userService.update(userMapper.toUser(userDto), userId);
        return userMapper.toContactDto(editedUser);
    }

    @GetMapping("/{id}")
    public ContactDto view(@PathVariable("id") Long userId) {
        User user = userService.get(userId);
        return userMapper.toContactDto(user);
    }

    @DeleteMapping("/{id}")
    public void erase(@PathVariable("id") Long idForDelete,
                      @RequestHeader("X-User-ID") Long userId) {
        userService.remove(idForDelete, userId);
    }

}