package ru.serggge.util;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.serggge.service.PkiService;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final PkiService pkiService;

    public String generateAccessToken(Long userId) {
        return Jwts.builder()
                   .header()
                   .type("JWT")
                   .add("alg", pkiService.getKeyAlgorithm())
                   .add("kid", pkiService.getPublicKeyIdentifier())
                   .and()
                   .issuer(pkiService.getIssuer())
                   .subject(Long.toString(userId))
                   .expiration(Date.from(Instant.now()
                                                .plus(pkiService.getTokenValidityPeriod(), ChronoUnit.MINUTES)))
                   .signWith(pkiService.getPrivateKey())
                   .compact();
    }

    public String extractLogin(String token) {
        return Jwts.parser()
                   .verifyWith(pkiService.getPublicKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .getSubject();
    }

    public Date extractExpirationTime(String token) {
        return Jwts.parser()
                   .verifyWith(pkiService.getPublicKey())
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
