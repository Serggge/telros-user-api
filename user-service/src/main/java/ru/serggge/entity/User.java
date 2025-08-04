package ru.serggge.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "surname")
    private String surname;
    @Column(name = "birth_date")
    private LocalDate birthday;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "active")
    private Boolean isActive = true;

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb.append(lastName).append(" ").append(firstName);
        if (surname != null) {
            sb.append(" ").append(surname);
        }
        return sb.toString();
    }
}