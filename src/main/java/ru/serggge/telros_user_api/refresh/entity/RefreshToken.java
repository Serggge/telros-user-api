package ru.serggge.telros_user_api.refresh.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.serggge.telros_user_api.user.entity.User;
import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
    @Column(name = "token")
    String token;
    @Column(name = "expired_time")
    Instant expiredTime;
}
