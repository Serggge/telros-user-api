package ru.serggge.telros_user_api.controller.admin;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;
import ru.serggge.telros_user_api.controller.admin.dto.ViewAllUserDtoResponse;
import ru.serggge.telros_user_api.controller.admin.dto.ViewUserDtoResponse;
import ru.serggge.telros_user_api.controller.login.dto.RoleSettable;
import ru.serggge.telros_user_api.model.entity.User;
import java.util.List;

@Component
public class AdminMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public ViewUserDtoResponse toViewUserDtoResponse(User user) {
        return toFullUserInfoDto(user, ViewUserDtoResponse.class);
    }

    public List<ViewAllUserDtoResponse> toViewAllUserDtoResponse(List<User> users) {
        return users.stream()
                .map(this::toViewAllUserDtoResponse)
                .toList();
    }

    private ViewAllUserDtoResponse toViewAllUserDtoResponse(User user) {
        return toFullUserInfoDto(user, ViewAllUserDtoResponse.class);
    }

    private  <T extends RoleSettable> T toFullUserInfoDto(User user, Class<T> clazz) {
        TypeMap<User, T> propertyMapper = modelMapper.createTypeMap(User.class, clazz);
        propertyMapper.addMappings(mapper -> mapper.map(src -> src.getRole().getRoleType(), T::setRole));
        return modelMapper.map(user, clazz);
    }


}
