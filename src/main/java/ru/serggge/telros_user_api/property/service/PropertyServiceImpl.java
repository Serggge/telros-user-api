package ru.serggge.telros_user_api.property.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Service;
import ru.serggge.telros_user_api.property.model.Property;
import ru.serggge.telros_user_api.property.model.PropertyStorage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private static final String DYNAMIC_PROPERTIES_SOURCE_NAME = "dynamicProperties";
    private final ConfigurableEnvironment environment;
    private final PropertyStorage propertyStorage;

    @Override
    public List<Property> getAll() {
        return propertyStorage.getProperties();
    }

    @Override
    public void add(Property property) {
        MutablePropertySources propertySources = environment.getPropertySources();
        if (!propertySources.contains(DYNAMIC_PROPERTIES_SOURCE_NAME)) {
            Map<String, Object> dynamicProperties = new HashMap<>();
            dynamicProperties.put(property.getKey(), property.getValue());
            propertySources.addFirst(new MapPropertySource(DYNAMIC_PROPERTIES_SOURCE_NAME, dynamicProperties));
        } else {
            MapPropertySource propertySource = (MapPropertySource) propertySources.get(DYNAMIC_PROPERTIES_SOURCE_NAME);
            propertySource.getSource().put(property.getKey(), property.getValue());
        }
    }
}
