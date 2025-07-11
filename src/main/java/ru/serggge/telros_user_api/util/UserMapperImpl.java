package ru.serggge.telros_user_api.util;

import org.springframework.stereotype.Component;
import ru.serggge.telros_user_api.model.ContactDto;
import ru.serggge.telros_user_api.model.User;
import ru.serggge.telros_user_api.model.UserDto;

@Component
public class UserMapperImpl implements UserMapper {

    public ContactDto toContactDto(User user) {
        String fullName = String.join(" ", user.getLastName() + user.getFirstName() + user.getSurname());
        return ContactDto.builder()
                .name(fullName)
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    @Override
    public User toUser(UserDto userDto) {
        return User.builder()
                .lastName(userDto.lastName())
                .firstName(userDto.firstName())
                .surname(userDto.surname())
                .birthday(userDto.birthday())
                .email(userDto.email())
                .phoneNumber(userDto.phoneNumber())
                .build();
    }
}
