package ru.serggge.security.service;

import io.jsonwebtoken.io.Decoders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.serggge.security.entity.PublicKeyEntity;
import ru.serggge.security.repository.PublicKeyRepository;
import ru.serggge.security.model.PkInfo;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PkiServiceImpl implements PkiService {

    private static final Map<String, PublicKey> publicKeys = new HashMap<>();
    private final PublicKeyRepository publicKeyRepository;

    @Override
    public PublicKey getPublicKey(PkInfo pkInfo) {
        // looking for a public key ID in the cache
        if (publicKeys.containsKey(pkInfo.getPublicKeyId())) {
            return publicKeys.get(pkInfo.getPublicKeyId());
        }
        // else, looking in the database
        PublicKeyEntity pk = publicKeyRepository.findByKeyIdentifierAndIsActiveTrue(pkInfo.getPublicKeyId())
                                                               .orElseThrow(() -> new RuntimeException("Public key not found"));
        // if we found it, then we build a public key by serialized value
        String pkSerialized = pk.getPublicKey();
        PublicKey publicKey = buildPublicKey(pkSerialized, pkInfo.getAlgorithm());
        // and save it in the cache
        publicKeys.put(pkInfo.getPublicKeyId(), publicKey);
        return publicKey;
    }

    private static PublicKey buildPublicKey(String pkSerialized, String algorithm) {
        // Base64 Decode
        byte[] decodedBytes = Decoders.BASE64.decode(pkSerialized);
        // Create Key Specification
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedBytes);
        // Generate Public Key Object
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Applying algorithm error", e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("Key spec error", e);
        }
    }
}