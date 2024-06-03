package ru.hse.authorization.repository.dto;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "session")
@RequiredArgsConstructor
public class SessionInRepository implements Serializable {

    public SessionInRepository(Long user_id, String tocken, Timestamp expires) {
        this.user_id = user_id;
        this.tocken = tocken;
        this.expires = expires;
    }

    public Long getCurrentId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    public Long user_id;

    @Column(name = "tocken", nullable = false)
    public String tocken;

    @Column(name = "expires", nullable = false)
    public Timestamp expires;

}
