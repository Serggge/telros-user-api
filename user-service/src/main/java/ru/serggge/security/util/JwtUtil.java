package ru.serggge.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.serggge.security.model.PkInfo;
import ru.serggge.security.service.PkiService;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@AllArgsConstructor
public class JwtUtil {

    private final PkiService pkiService;


    public String extractSubjectId(String token) {
        // из токена извлекаем заголовок, а из него получаем айди public key и алгоритм шифрования
        PkInfo pkInfo = extractPublicKeyId(token);
        // получаем публичный ключ
        PublicKey publicKey = pkiService.getPublicKey(pkInfo);
        // извлекаем из тела токена авторизационный айди пользователя, отправившего токен
        return Jwts.parser()
                   .verifyWith(publicKey)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .getSubject();
    }

    public boolean validateToken(String token, String username) {
        PkInfo pkInfo = extractPublicKeyId(token);
        Claims claims = extractClaims(token, pkInfo);
        String subject = claims.getSubject();
        Date expiration = claims.getExpiration();
        return Objects.nonNull(subject) && Objects.nonNull(expiration)
                && Objects.equals(subject, username)
                && expiration.after(Date.from(Instant.now()));
    }

    private Claims extractClaims(String token, PkInfo pkInfo) {
        return Jwts.parser()
                   .verifyWith(pkiService.getPublicKey(pkInfo))
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    }

    private PkInfo extractPublicKeyId(String token) {
        String encodedHeader = token.substring(0, token.indexOf('.'));
        byte[] decodeHeader = Decoders.BASE64.decode(encodedHeader);
        String header = new String(decodeHeader);
        try {
            // находим айди
            Matcher matcher = Pattern.compile("\"alg\":\"(?<alg>[^\"]*)").matcher(header);
            String algorithm = matcher.find() ? matcher.group("alg") : null;
            matcher = Pattern.compile("\"kid\":\"(?<publicId>[^\"]*)")
                                     .matcher(header);
            String publicKeyId = matcher.find() ? matcher.group("publicId") : null;

            return new PkInfo(publicKeyId, algorithm);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}