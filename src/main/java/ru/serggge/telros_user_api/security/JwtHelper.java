package ru.serggge.telros_user_api.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

@Component
@RefreshScope
public class JwtHelper {

    private final SecretKey secretKey = Jwts.SIG.HS256.key().build();
    @Value("${access-token.expiration.time.mins}")
    private Long tokenExpirationTime;

    public boolean validateAccessToken(String token, UserDetails userDetails) {
        String login = extractLogin(token);
        Date expirationDate = extractExpirationdDate(token);
        if (Objects.nonNull(login) && Objects.nonNull(expirationDate)) {
            return login.equals(userDetails.getUsername())
                    && Date.from(Instant.now()).before(expirationDate);
        } else {
            return false;
        }
    }

    public String createAccessToken(String login) {
        return Jwts.builder()
                   .claim("login", login)
                   .issuer("telros-user-api")
                   .issuedAt(Date.from(Instant.now()))
                   .expiration(Date.from(Instant.now()
                                                .plus(tokenExpirationTime, ChronoUnit.MINUTES)))
                   .signWith(getSigningKey())
                   .compact();
    }

    public String extractLogin(String token) {
        return Jwts.parser()
                   .verifyWith(getSigningKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .get("login", String.class);
    }

    public Date extractExpirationdDate(String token) {
        return Jwts.parser()
                   .verifyWith(getSigningKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .getExpiration();
    }

    private SecretKey getSigningKey() {
        return secretKey;
    }
}
