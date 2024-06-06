package ru.hse.ordering.services.dto;

import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;


@RequiredArgsConstructor
public class OrderInService{

    private Long id;

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

    public Long userId;

    public Long from_station_id;

    public Long to_station_id;

    public Integer status;

    public Timestamp created;
}