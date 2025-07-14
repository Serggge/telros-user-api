package ru.serggge.telros_user_api.controller.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.serggge.telros_user_api.controller.user.dto.*;
import java.util.List;

public interface UserOperations {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@RequestBody @Valid RegisterRequest dto);

    @PatchMapping
    public EditResponse edit(@RequestBody @Valid EditRequest dto,
                             @RequestHeader("X-User-ID") Long userId);
    @GetMapping("/{id}")
    public ViewResponse view(@PathVariable("id") Long userId);

    @GetMapping
    public List<ViewResponse> viewAll();

    @DeleteMapping("/{id}")
    public void erase(@PathVariable("id") Long idForDelete,
                      @RequestHeader("X-User-ID") Long userId);

}