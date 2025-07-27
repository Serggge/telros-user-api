package ru.serggge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.serggge.entity.Credentials;
import ru.serggge.entity.RefreshToken;
import ru.serggge.model.AccessToken;
import ru.serggge.repository.CredentialRepository;
import ru.serggge.repository.RefreshRepository;
import ru.serggge.util.JwtUtil;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import static org.springframework.transaction.annotation.Propagation.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final CredentialRepository credentialRepository;
    private final RefreshRepository refreshRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    @Value("${token.validity.refresh}")
    private int tokenValidityPeriod;

    @Override
    @Transactional
    public AccessToken register(Credentials credentials) {
        if (credentialRepository.findByLoginIgnoreCaseAndActiveTrue(credentials.getLogin()).isPresent()) {
            throw new RuntimeException("Login already registered");
        }
        String encodedPasswd = passwordEncoder.encode(credentials.getPassword());
        Credentials encodedCredentials = new Credentials(null, credentials.getLogin(), encodedPasswd, true);
        credentialRepository.save(encodedCredentials);
        return login(credentials);
    }

    @Override
    public AccessToken login(Credentials credentials) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                credentials.getLogin(), credentials.getPassword()));
        String accessToken = jwtUtil.generateAccessToken(credentials.getLogin());
        return new AccessToken(accessToken);
    }

    @Override
    @Transactional
    public RefreshToken createRefreshToken(Credentials credentials) {
        if (refreshRepository.findByCredentialsLogin(credentials.getLogin()).isEmpty()) {
            String generatedToken = UUID.randomUUID().toString();
            RefreshToken refreshToken = RefreshToken.builder()
                                                    .credentials(credentials)
                                                    .token(generatedToken)
                                                    .expiredTime(Instant.now().plus(tokenValidityPeriod, ChronoUnit.DAYS))
                                                    .isActive(true)
                                                    .build();
            return refreshRepository.save(refreshToken);
        } else {
            throw new RuntimeException("User already registered");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public RefreshToken getRefreshToken(Credentials credentials) {
        RefreshToken refreshToken = refreshRepository.findByCredentialsLogin(credentials.getLogin())
                                                     .orElseThrow(() -> new RuntimeException("Login unknown"));
        return refreshToken.getExpiredTime().isAfter(Instant.now())
                ? refreshToken : updateRefreshToken(refreshToken.getToken());
    }

    @Override
    @Transactional(propagation = REQUIRED)
    public RefreshToken updateRefreshToken(String oldRefreshToken) {
        RefreshToken refreshToken = refreshRepository.findByToken(oldRefreshToken)
                                                     .orElseThrow(() -> new RuntimeException("Can't find the token"));
        String generatedToken = UUID.randomUUID().toString();
        refreshToken.setToken(generatedToken);
        refreshToken.setExpiredTime(Instant.now().plus(tokenValidityPeriod, ChronoUnit.DAYS));
        return refreshRepository.save(refreshToken);
    }

    @Override
    public AccessToken updateAccessToken(Credentials credentials) {
        String generatedToken = jwtUtil.generateAccessToken(credentials.getLogin());
        return new AccessToken(generatedToken);
    }
}