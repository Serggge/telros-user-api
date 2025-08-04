package ru.serggge.security.service;

import ru.serggge.security.model.PkInfo;

import java.security.PublicKey;

public interface PkiService {

    PublicKey getPublicKey(PkInfo pkInfo);
}
