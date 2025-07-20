package ru.serggge.telros_user_api.refresh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.serggge.telros_user_api.login.model.AccessToken;
import ru.serggge.telros_user_api.refresh.repository.RefreshRepository;
import ru.serggge.telros_user_api.refresh.entity.RefreshToken;
import ru.serggge.telros_user_api.security.JwtHelper;
import ru.serggge.telros_user_api.user.entity.User;
import ru.serggge.telros_user_api.user.repository.UserRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RefreshScope
@RequiredArgsConstructor
public class RefreshServiceImpl implements RefreshService {

    private final RefreshRepository refreshRepository;
    private final UserRepository userRepository;
    private final JwtHelper jwtHelper;
    @Value("${refresh-token.expiration.time.days}")
    private Long tokenExpirationTime;

    @Override
    public RefreshToken get(String token, String login) {
        RefreshToken refreshToken = refreshRepository.findByUserCredentialLoginAndToken(login, token)
                                                     .orElseThrow(() ->
                                                             new RuntimeException(("Login not found or refresh token invalid")));
        // если рефреш токен истекший по времени, то создаём новый
        if (refreshToken.getExpiredTime().isBefore(Instant.now())) {
            String generatedToken = UUID.randomUUID().toString();
            refreshToken.setToken(generatedToken);
            refreshToken.setExpiredTime(Instant.now().plus(tokenExpirationTime, ChronoUnit.DAYS));
            refreshToken = refreshRepository.save(refreshToken);
        }
        return  refreshToken;
    }

    @Override
    @Transactional
    public RefreshToken createRefreshToken(String login) {
        // генерируем рефреш токен
        String generatedToken = UUID.randomUUID().toString();
        // получаем пользователя, соответствующего логину
        User owner = userRepository.findUserByCredentialLoginAndIsActiveTrue(login)
                                   .orElseThrow(() -> new RuntimeException("Invalid login"));
        // создаём энтити, содержащий рефреш токен, и записываем в БД
        RefreshToken refreshToken = RefreshToken.builder()
                                         .user(owner)
                                         .token(generatedToken)
                                         .expiredTime(Instant.now()
                                                             .plus(tokenExpirationTime, ChronoUnit.DAYS))
                                         .build();
        return refreshRepository.save(refreshToken);
    }

    @Override
    public AccessToken createAccessToken(String login) {
        String accessToken = jwtHelper.createAccessToken(login);
        return new AccessToken(accessToken);
    }
}
