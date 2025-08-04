package ru.serggge.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PkInfo {

    private String publicKeyId;
    private String algorithm;
}
