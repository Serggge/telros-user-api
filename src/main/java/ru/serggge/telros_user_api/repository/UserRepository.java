package ru.serggge.telros_user_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.serggge.telros_user_api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select count (u) > 0 " +
            "from User u " +
            "WHERE u.email like ?1")
    boolean existsByEmail(String email);

}
