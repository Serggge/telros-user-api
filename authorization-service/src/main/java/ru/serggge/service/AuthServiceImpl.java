package ru.serggge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.serggge.entity.Credentials;
import ru.serggge.model.RefreshToken;
import ru.serggge.model.AccessToken;
import ru.serggge.repository.CredentialRepository;
import ru.serggge.util.JwtUtil;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final CredentialRepository credentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final Map<Long, RefreshToken> refreshTokens = new HashMap<>();
    @Value("${token.validity.refresh}")
    private int tokenValidityPeriod;

    @Override
    @Transactional
    public AccessToken register(Credentials credentials) {
        if (credentialRepository.findByLoginIgnoreCaseAndIsActiveTrue(credentials.getLogin())
                                .isPresent()) {
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
        credentials = credentialRepository.findByLoginIgnoreCaseAndIsActiveTrue(credentials.getLogin())
                                          .orElseThrow(() -> new UsernameNotFoundException("Login not found"));
        String accessToken = jwtUtil.generateAccessToken(credentials.getId());
        return new AccessToken(accessToken);
    }

    @Override
    public RefreshToken createRefreshToken(Long userId) {
        String generatedToken = UUID.randomUUID().toString();
        Date expirationDate = Date.from(Instant.now()
                                               .plus(tokenValidityPeriod, ChronoUnit.DAYS));
        RefreshToken refreshToken = new RefreshToken(userId, generatedToken, expirationDate);
        refreshTokens.put(userId, refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshToken updateRefreshToken(String oldRefreshToken) {
        RefreshToken token = refreshTokens.values()
                                          .stream()
                                          .filter(refreshToken -> refreshToken.getToken()
                                                                              .equals(oldRefreshToken))
                                          .findFirst()
                                          .orElseThrow(() -> new RuntimeException("Refresh token not found"));
        return createRefreshToken(token.getUserId());
    }

    @Override
    public RefreshToken getRefreshToken(Long userId) {
        return refreshTokens.get(userId);
    }

    @Override
    public AccessToken updateAccessToken(Long userId) {
        String generatedToken = jwtUtil.generateAccessToken(userId);
        return new AccessToken(generatedToken);
    }
}