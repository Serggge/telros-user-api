package ru.serggge.telros_user_api.refresh.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.serggge.telros_user_api.refresh.repository.RefreshRepository;
import ru.serggge.telros_user_api.refresh.entity.RefreshToken;
import ru.serggge.telros_user_api.user.entity.User;
import ru.serggge.telros_user_api.user.repository.UserRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshServiceImpl implements RefreshService {

    private final RefreshRepository refreshRepository;
    private final UserRepository userRepository;

    @Override
    public RefreshToken get(String token, String login) {
        return refreshRepository.findByUserCredentialLoginAndToken(login, token).orElseThrow(() ->
                new RuntimeException(("Login not found or refresh token invalid")));
    }

    @Override
    public RefreshToken createRefreshToken(String login) {
        String generatedToken = UUID.randomUUID()
                            .toString();
        User owner = userRepository.findUserByCredentialLogin(login).orElseThrow(() -> new RuntimeException("Invalid login"));
        RefreshToken refreshToken = RefreshToken.builder()
                                         .user(owner)
                                         .token(generatedToken)
                                         .expiredTime(Instant.now()
                                                             .plus(1L, ChronoUnit.DAYS))
                                         .build();
        return refreshRepository.save(refreshToken);
    }
}
