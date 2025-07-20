package ru.serggge.telros_user_api.property.model;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import java.util.LinkedList;
import java.util.List;

@RefreshScope
@Component
public class PropertyStorage {

    @Value("${refresh-token.expiration.time.days}")
    @Getter
    private Long refreshTokenExpiration;
    @Value("${access-token.expiration.time.mins}")
    @Getter
    private Long accessTokenExpiration;
    @Value("${spring.datasource.username}")
    private String dbUserName;
    @Value("${spring.datasource.password}")
    private String dbPassword;

    public List<Property> getProperties() {
        List<Property> publicProperties = new LinkedList<>();
        publicProperties.add(new Property("Refresh token expiration time (days)", refreshTokenExpiration));
        publicProperties.add(new Property("Access token expiration time (minutes)", accessTokenExpiration));
        return publicProperties;
    }
}