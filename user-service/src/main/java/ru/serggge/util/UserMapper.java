package ru.serggge.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.serggge.dto.*;
import ru.serggge.model.UserInfo;
import ru.serggge.entity.User;
import java.util.List;

@Component
public class UserMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public User createRequestToUser(CreateRequest dto) {
        return modelMapper.map(dto, User.class);
    }

    public User editRequestToUser(EditRequest dto) {
        return modelMapper.map(dto, User.class);
    }

    public UserInfo toRegisterDto(User user) {
        UserContacts contactDto = toContactDto(user);
        return modelMapper.map(contactDto, UserInfo.class);
    }

    public EditResponse toEditDto(User user) {
        UserContacts contactDto = toContactDto(user);
        return modelMapper.map(contactDto, EditResponse.class);
    }

    public ViewResponse toViewDto(User user) {
        UserContacts contactDto = toContactDto(user);
        return modelMapper.map(contactDto, ViewResponse.class);
    }

    public List<ViewResponse> toViewDtoList(List<User> users) {
        return users.stream()
                    .map(this::toViewDto)
                    .toList();
    }

    private UserContacts toContactDto(User user) {
        String userFullName = getFullName(user);
        return UserContacts.builder()
                           .person(userFullName)
                           .email(user.getEmail())
                           .phoneNumber(user.getPhoneNumber())
                           .build();
    }

    private String getFullName(User user) {
        StringBuilder builder = new StringBuilder()
                .append(user.getLastName())
                .append(user.getFirstName());
        if (user.getSurname() != null) {
            builder.append(" ")
                   .append(user.getSurname());
        }
        return builder.toString();
    }
}