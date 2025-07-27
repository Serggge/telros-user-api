package ru.serggge.util;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtUtil {

    private final SecretKey secretKey = Jwts.SIG.HS256.key()
                                                      .random(new SecureRandom())
                                                      .build();
    @Value("${issuer}")
    private String issuer;
    @Value("${token.validity.access}")
    private int tokenValidityPeriod;


    public String generateAccessToken(String login) {
        return Jwts.builder()
                   .issuer(issuer)
                   .subject(login)
                   .expiration(Date.from(Instant.now()
                                                .plus(tokenValidityPeriod, ChronoUnit.MINUTES)))
                   .signWith(secretKey)
                   .compact();
    }

    public String extractLogin(String token) {
        return Jwts.parser()
                   .verifyWith(secretKey)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .getSubject();
    }

    public Date extractExpirationTime(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    public boolean validateToken(String token, String login) {
        String subject = extractLogin(token);
        Date expirationTime = extractExpirationTime(token);
        return Objects.nonNull(subject) && Objects.nonNull(expirationTime)
                && subject.equalsIgnoreCase(login)
                && expirationTime.after(Date.from(Instant.now()));
    }
}
