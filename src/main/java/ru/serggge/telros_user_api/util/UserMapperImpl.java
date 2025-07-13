package ru.serggge.telros_user_api.util;

import org.springframework.stereotype.Component;
import ru.serggge.telros_user_api.model.dto.ContactDto;
import ru.serggge.telros_user_api.model.dto.Credentials;
import ru.serggge.telros_user_api.model.dto.FullUserInfoDto;
import ru.serggge.telros_user_api.model.entity.User;
import ru.serggge.telros_user_api.model.dto.UserInfoDto;

import java.util.List;
import java.util.Objects;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public ContactDto toContactDto(User user) {
        StringBuilder fullName = new StringBuilder()
                .append(user.getFirstName())
                .append(" ")
                .append(user.getLastName());
        if (Objects.nonNull(user.getSurname()) && !user.getSurname()
                                                       .isBlank()) {
            fullName.append(" ")
                    .append(user.getSurname());
        }

        return ContactDto.builder()
                         .person(fullName.toString())
                         .email(user.getEmail())
                         .phoneNumber(user.getPhoneNumber())
                         .build();
    }

    @Override
    public List<ContactDto> toContactDto(List<User> users) {
        return users.stream()
                    .map(this::toContactDto)
                    .toList();
    }

    @Override
    public User toUser(UserInfoDto userInfoDto) {
        return User.builder()
                   .lastName(userInfoDto.lastName())
                   .firstName(userInfoDto.firstName())
                   .surname(userInfoDto.surname())
                   .birthday(userInfoDto.birthday())
                   .email(userInfoDto.email())
                   .phoneNumber(userInfoDto.phoneNumber())
                   .build();
    }

    @Override
    public FullUserInfoDto toFullUserInfo(User user) {
        Credentials credentialDto;
        if (Objects.nonNull(user.getCredential())) {
            credentialDto = new Credentials(user.getCredential().getLogin(), user.getCredential().getPassword());
        } else {
            credentialDto = new Credentials("not present", "not present");
        }
        return FullUserInfoDto.builder()
                              .id(user.getId())
                              .firstName(user.getFirstName())
                              .lastName(user.getLastName())
                              .surname(user.getSurname())
                              .birthday(user.getBirthday())
                              .email(user.getEmail())
                              .phoneNumber(user.getPhoneNumber())
                              .role(user.getRole()
                                        .getRoleType())
                              .credentialDto(credentialDto)
                              .build();
    }

    @Override
    public List<FullUserInfoDto> toFullUserInfo(List<User> users) {
        return users.stream()
                    .map(this::toFullUserInfo)
                    .toList();
    }
}
