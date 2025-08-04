package ru.serggge.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.serggge.dto.CreateRequest;
import ru.serggge.dto.EditRequest;
import ru.serggge.dto.EditResponse;
import ru.serggge.dto.ViewResponse;
import ru.serggge.model.UserInfo;
import java.util.List;

public interface UserOperations {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserInfo create(@RequestBody @Valid CreateRequest request);

    @PatchMapping
    EditResponse edit(@RequestBody @Valid EditRequest request,
                             @RequestHeader("X-User-ID") Long userId);
    @GetMapping("/{id}")
    ViewResponse view(@PathVariable("id") Long userId);

    @GetMapping
    List<ViewResponse> viewAll();

    @DeleteMapping("/{id}")
    void erase(@PathVariable("id") Long idForDelete,
                      @RequestHeader("X-User-ID") Long userId);

}