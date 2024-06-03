package ru.hse.authorization.services.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@RequiredArgsConstructor
public class SessionInService {
    public SessionInService(Long id, Long user_id, String tocken, Timestamp expires) {
        this.id = id;
        this.user_id = user_id;
        this.tocken = tocken;
        this.expires = expires;
    }

    public Long getCurrentId() {
        return id;
    }


    private Long id;


    public Long user_id;


    public String tocken;


    public Timestamp expires;
}
