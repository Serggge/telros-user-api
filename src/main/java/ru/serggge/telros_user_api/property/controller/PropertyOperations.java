package ru.serggge.telros_user_api.property.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.serggge.telros_user_api.property.dto.PropertyResponse;
import ru.serggge.telros_user_api.property.dto.PropertyUpdateRequest;
import ru.serggge.telros_user_api.property.dto.PropertyUpdateResponse;

import java.util.List;

@RequestMapping("/properties")
public interface PropertyOperations {

    @PatchMapping
    PropertyUpdateResponse update(@RequestBody @Valid PropertyUpdateRequest request);

    @GetMapping
    List<PropertyResponse> viewProperties();
}
