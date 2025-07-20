package ru.serggge.telros_user_api.property.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyUpdateRequest {

    @NotBlank
    private String key;
    @NotBlank
    private String value;
}
