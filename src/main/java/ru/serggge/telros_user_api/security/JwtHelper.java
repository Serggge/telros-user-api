package ru.serggge.telros_user_api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

@Component
@Slf4j
public class JwtHelper {

    @Value("${jwt.token.secret}")
    private String jwtSecret;

    public boolean validateToken(String token, UserDetails userDetails) {
        String login = extractLogin(token);
        String password = extractPassword(token);
        Date expirationDate = extractExpirationdDate(token);
        if (Objects.nonNull(login) && Objects.nonNull(password) && Objects.nonNull(expirationDate)) {
            return login.equals(userDetails.getUsername())
                    && password.equals(userDetails.getPassword())
                    && Date.from(Instant.now()).before(expirationDate);
        } else {
            return false;
        }
    }

    public String createJwtToken(String login, String password) {
        return Jwts.builder()
                   .claim("login", login)
                   .claim("password", password)
                   .issuer("telros-user-api")
                   .issuedAt(Date.from(Instant.now()))
                   .expiration(Date.from(Instant.now()
                                                .plus(30, ChronoUnit.MINUTES)))
                   .signWith(getSigningKey())
                   .compact();
    }

    public Jws<Claims> parseJwtToken(String jwtString) {
        return Jwts.parser()
                   .verifyWith(getSigningKey())
                   .build()
                   .parseSignedClaims(jwtString);
    }

    public String extractLogin(String token) {
        return Jwts.parser()
                   .verifyWith(getSigningKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .get("login", String.class);
    }

    public String extractPassword(String token) {
        return Jwts.parser()
                   .verifyWith(getSigningKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .get("password", String.class);
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
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
