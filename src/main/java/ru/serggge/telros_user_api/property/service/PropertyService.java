package ru.serggge.telros_user_api.property.service;

import ru.serggge.telros_user_api.property.model.Property;

import java.util.List;

public interface PropertyService {

    List<Property> getAll();

    void add(Property property);
}