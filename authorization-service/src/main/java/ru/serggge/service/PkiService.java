package ru.serggge.service;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface PkiService {

    String getPublicKeyIdentifier();

    PublicKey getPublicKey();

    PrivateKey getPrivateKey();

    String getIssuer();

    String getKeyAlgorithm();

    Long getTokenValidityPeriod();
}