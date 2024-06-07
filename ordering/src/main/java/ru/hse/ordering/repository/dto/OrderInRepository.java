package ru.hse.ordering.repository.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Table(name = "order_table")
@RequiredArgsConstructor
public class OrderInRepository implements Serializable {

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "from_station_id", nullable = false)
    private Long from_station_id;

    @Column(name = "to_station_id", nullable = false)
    private Long to_station_id;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "created", nullable = false)
    private Timestamp created;


}
