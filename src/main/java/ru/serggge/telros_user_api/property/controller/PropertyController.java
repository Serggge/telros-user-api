package ru.serggge.telros_user_api.property.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.serggge.telros_user_api.property.dto.PropertyResponse;
import ru.serggge.telros_user_api.property.dto.PropertyUpdateRequest;
import ru.serggge.telros_user_api.property.dto.PropertyUpdateResponse;
import ru.serggge.telros_user_api.property.model.Property;
import ru.serggge.telros_user_api.property.service.PropertyService;
import ru.serggge.telros_user_api.property.util.PropertyMapper;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PropertyController implements PropertyOperations {

    private final PropertyService propertyService;
    private final PropertyMapper propertyMapper;

    @Override
    public PropertyUpdateResponse update(PropertyUpdateRequest request) {
        Property property = propertyMapper.toProperty(request);
        propertyService.add(property);
        return new PropertyUpdateResponse();
    }

    @Override
    public List<PropertyResponse> viewProperties() {
        List<Property> properties = propertyService.getAll();
        return propertyMapper.toPropertyResponse(properties);
    }
}
