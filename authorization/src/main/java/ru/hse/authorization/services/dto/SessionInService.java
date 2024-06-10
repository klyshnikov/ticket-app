package ru.hse.authorization.services.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
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

    private Long user_id;

    private String tocken;

    private Timestamp expires;
}
