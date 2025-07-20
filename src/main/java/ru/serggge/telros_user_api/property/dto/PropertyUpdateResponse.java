package ru.serggge.telros_user_api.property.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyUpdateResponse {

    private String message = "Property updated. Remember to call the actuator /actuator/refresh";
}
