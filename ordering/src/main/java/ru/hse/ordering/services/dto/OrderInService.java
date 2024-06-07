package ru.hse.ordering.services.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderInService{

    public Long getCurrentId() {
        return id;
    }

    public OrderInService(
            Long id,
            Long user_id,
            Long from_station_id,
            Long to_station_id,
            Integer status,
            Timestamp created
    ) {
        this.id = id;
        this.userId = user_id;
        this.from_station_id = from_station_id;
        this.to_station_id = to_station_id;
        this.status = status;
        this.created = created;
    }

    private Long id;

    private Long userId;

    private Long from_station_id;

    private Long to_station_id;

    private Integer status;

    private Timestamp created;
}