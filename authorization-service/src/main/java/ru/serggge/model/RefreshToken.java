package ru.serggge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {

    private Long userId;
    private String token;
    private Date expiration;
}