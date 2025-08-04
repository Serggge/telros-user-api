package ru.serggge.service;

import io.jsonwebtoken.io.Encoders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.serggge.entity.PublicKeyEntity;
import ru.serggge.repository.PublicKeyRepository;
import java.security.*;
import java.util.UUID;

@Component
public class PkiServiceImpl implements PkiService {

    private static String publicKeyId;
    private final PublicKeyRepository publicKeyRepository;
    private final PublicKey publicKey;
    private final PrivateKey privateKey;
    private final String issuer;
    private long tokenValidityPeriod;
    private String keyAlgorithm;

    public PkiServiceImpl(PublicKeyRepository publicKeyRepository,
                          @Value("${token.issuer}") String issuer,
                          @Value("${token.validity.access}") long tokenValidityPeriod,
                          @Value("${token.secret-key.algorithm}") String keyAlgorithm,
                          @Value("${token.secret-key.bit-size}") int keyBitSize) {
        this.publicKeyRepository = publicKeyRepository;
        this.issuer = issuer;
        this.tokenValidityPeriod = tokenValidityPeriod;
        this.keyAlgorithm = keyAlgorithm;
        KeyPair keyPair = generateKeyPair(keyAlgorithm, keyBitSize);
        this.publicKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
    }

    @Override
    @Transactional
    public String getPublicKeyIdentifier() {
        if (publicKeyId == null) {
            publicKeyRepository.findByIssuerAndIsActive(issuer, true)
                               .ifPresent(publicKey -> {
                                   publicKey.setActive(false);
                                   publicKeyRepository.save(publicKey);
                               });
            publicKeyId = generatePublicKeyId();
            String pkSerialized = serializePublicKey();
            PublicKeyEntity pk = new PublicKeyEntity(null, publicKeyId, pkSerialized, issuer, true);
            publicKeyRepository.save(pk);
        }
        return publicKeyId;
    }

    @Override
    public PublicKey getPublicKey() {
        return publicKey;
    }

    @Override
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    @Override
    public String getIssuer() {
        return issuer;
    }

    @Override
    public String getKeyAlgorithm() {
        return keyAlgorithm;
    }

    @Override
    public Long getTokenValidityPeriod() {
        return tokenValidityPeriod;
    }

    private KeyPair generateKeyPair(String algorithm, int keyBitSize) {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating key pair generator", e);
        }
        keyPairGenerator.initialize(keyBitSize, new  SecureRandom());
        return keyPairGenerator.generateKeyPair();
    }

    private String generatePublicKeyId() {
        return UUID.randomUUID()
                   .toString();
    }

    private String serializePublicKey() {
        return Encoders.BASE64.encode(publicKey.getEncoded());
    }
}