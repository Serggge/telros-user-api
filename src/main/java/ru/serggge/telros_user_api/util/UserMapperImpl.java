package ru.serggge.telros_user_api.util;

import org.springframework.stereotype.Component;
import ru.serggge.telros_user_api.model.dto.ContactDto;
import ru.serggge.telros_user_api.model.dto.CredentialDto;
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
                .append(user.getLastName())
                .append(" ")
                .append(user.getFirstName());
        if (Objects.nonNull(user.getSurname()) && !user.getSurname().isBlank()) {
            fullName.append(" ").append(user.getSurname());
        }
        return new ContactDto(fullName.toString(), user.getEmail(), user.getPhoneNumber());

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
                   .lastName(userInfoDto.getLastName())
                   .firstName(userInfoDto.getFirstName())
                   .surname(userInfoDto.getSurname())
                   .birthday(userInfoDto.getBirthday())
                   .email(userInfoDto.getEmail())
                   .phoneNumber(userInfoDto.getPhoneNumber())
                   .build();
    }

    @Override
    public FullUserInfoDto toFullUserInfo(User user) {
        CredentialDto credentials;
        if (Objects.nonNull(user.getCredential())) {
            credentials = new CredentialDto(user.getCredential().getLogin(), user.getCredential().getPassword());
        } else {
            credentials = new CredentialDto("not present", "not present");
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
                              .credentials(credentials)
                              .build();
    }

    @Override
    public List<FullUserInfoDto> toFullUserInfo(List<User> users) {
        return users.stream()
                    .map(this::toFullUserInfo)
                    .toList();
    }
}
