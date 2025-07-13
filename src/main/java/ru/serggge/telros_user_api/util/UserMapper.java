package ru.serggge.telros_user_api.util;

import ru.serggge.telros_user_api.model.dto.ContactDto;
import ru.serggge.telros_user_api.model.dto.FullUserInfoDto;
import ru.serggge.telros_user_api.model.entity.User;
import ru.serggge.telros_user_api.model.dto.UserInfoDto;

import java.util.List;

public interface UserMapper {

    ContactDto toContactDto(User user);

    List<ContactDto> toContactDto(List<User> users);

    User toUser(UserInfoDto userInfoDto);

    FullUserInfoDto toFullUserInfo(User user);

    List<FullUserInfoDto> toFullUserInfo(List<User> users);
}
