package ru.serggge.telros_user_api.util;

import ru.serggge.telros_user_api.model.ContactDto;
import ru.serggge.telros_user_api.model.User;
import ru.serggge.telros_user_api.model.UserDto;

public interface UserMapper {

    ContactDto toContactDto(User user);

    User toUser(UserDto userDto);
}
