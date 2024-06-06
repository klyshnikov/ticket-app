package ru.hse.ordering.repository.dto;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "order_table")
@RequiredArgsConstructor
public class OrderInRepository implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getCurrentId() {
        return id;
    }

    public OrderInRepository(
            Long user_id,
            Long from_station_id,
            Long to_station_id,
            Integer status,
            Timestamp created
    ) {
        this.userId = user_id;
        this.from_station_id = from_station_id;
        this.to_station_id = to_station_id;
        this.status = status;
        this.created = created;
    }

    public OrderInRepository(
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

    @Column(name = "user_id", nullable = false)
    public Long userId;

    @Column(name = "from_station_id", nullable = false)
    public Long from_station_id;

    @Column(name = "to_station_id", nullable = false)
    public Long to_station_id;

    @Column(name = "status", nullable = false)
    public Integer status;

    @Column(name = "created", nullable = false)
    public Timestamp created;


}
