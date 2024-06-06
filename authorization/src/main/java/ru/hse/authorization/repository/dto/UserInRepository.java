package ru.hse.authorization.repository.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
public class UserInRepository implements Serializable {

    public UserInRepository(String nickname, String email, String password, Timestamp timestamp) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.timestamp = timestamp;
    }

    public Long getCurrentId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nickname", nullable = false)
    public String nickname;

    @Column(name = "email", nullable = false, unique = true)
    public String email;

    @Column(name = "password", nullable = false)
    public String password;

    @Column(name = "created", nullable = false)
    public Timestamp timestamp;

}
