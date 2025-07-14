package ru.serggge.telros_user_api.controller.user;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;
import ru.serggge.telros_user_api.controller.user.dto.*;
import ru.serggge.telros_user_api.model.entity.User;
import java.util.List;

@Component
public class UserMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public User registerToUser(RegisterRequest dto) {
        return modelMapper.map(dto, User.class);
    }

    public User editToUser(EditRequest dto) {
        return modelMapper.map(dto, User.class);
    }

    public RegisterResponse toRegisterDto(User user) {
        return (RegisterResponse) toContactDto(user, RegisterResponse.class);
    }

    public EditResponse toEditDto(User user) {
        return (EditResponse) toContactDto(user, EditResponse.class);
    }

    public ViewResponse toViewDto(User user) {
        return (ViewResponse) toContactDto(user, ViewResponse.class);
    }

    public List<ViewResponse> toViewDtoList(List<User> users) {
        return users.stream()
                .map(this::toViewDto)
                .toList();
    }
    private <T extends Personable> Personable toContactDto(User user, Class<T> clazz) {
        TypeMap<User, T> propertyMapper = modelMapper.createTypeMap(User.class, clazz);
        propertyMapper.addMappings(mapper -> mapper.map(User::getFullName, Personable::setPerson));
        return modelMapper.map(user, clazz);
    }
}