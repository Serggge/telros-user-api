package ru.serggge.telros_user_api.property.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.serggge.telros_user_api.property.dto.PropertyResponse;
import ru.serggge.telros_user_api.property.dto.PropertyUpdateRequest;
import ru.serggge.telros_user_api.property.model.Property;
import java.util.List;

@Component
public class PropertyMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Property toProperty(PropertyUpdateRequest request) {
        return modelMapper.map(request, Property.class);
    }

    public PropertyResponse toPropertyResponse(Property property) {
        return modelMapper.map(property, PropertyResponse.class);
    }

    public List<PropertyResponse> toPropertyResponse(List<Property> properties) {
        return properties.stream()
                         .map(this::toPropertyResponse)
                         .toList();
    }
}
