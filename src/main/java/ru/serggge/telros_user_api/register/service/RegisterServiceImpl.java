package ru.serggge.telros_user_api.register.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.serggge.telros_user_api.login.model.AccessToken;
import ru.serggge.telros_user_api.login.service.LoginService;
import ru.serggge.telros_user_api.register.entity.Credential;
import ru.serggge.telros_user_api.register.repository.CredentialRepository;
import ru.serggge.telros_user_api.user.service.UserService;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final CredentialRepository credentialRepository;
    private final UserService userService;
    private final LoginService loginService;
    private final PasswordEncoder passwordEncoder;

    /**
     * @param credential get login and password
     * @return JWT token
     */
    @Override
    @Transactional
    public AccessToken createAccessToken(Credential credential) {
        final String login = credential.getLogin();
        final String password = credential.getPassword();
        Credential encodedCredential = encodeAndSave(credential);
        userService.createGuest(encodedCredential);
        return loginService.getToken(login, password);
    }

    private Credential encodeAndSave(Credential credential) {
        String encodedPassword = passwordEncoder.encode(credential.getPassword());
        credential.setPassword(encodedPassword);
        return credentialRepository.save(credential);
    }
}