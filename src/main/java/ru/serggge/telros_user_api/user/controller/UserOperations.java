package ru.serggge.telros_user_api.user.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.serggge.telros_user_api.user.dto.CreateRequest;
import ru.serggge.telros_user_api.user.dto.EditRequest;
import ru.serggge.telros_user_api.user.dto.EditResponse;
import ru.serggge.telros_user_api.user.dto.ViewResponse;
import ru.serggge.telros_user_api.user.model.UserInfo;
import java.util.List;

public interface UserOperations {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserInfo create(@RequestBody @Valid CreateRequest dto);

    @PatchMapping
    EditResponse edit(@RequestBody @Valid EditRequest dto,
                             @RequestHeader("X-User-ID") Long userId);
    @GetMapping("/{id}")
    ViewResponse view(@PathVariable("id") Long userId);

    @GetMapping
    List<ViewResponse> viewAll();

    @DeleteMapping("/{id}")
    void erase(@PathVariable("id") Long idForDelete,
                      @RequestHeader("X-User-ID") Long userId);

}