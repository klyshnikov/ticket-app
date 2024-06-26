package ru.hse.ordering.repository.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@Table(name = "station")
@RequiredArgsConstructor
public class StationInRepository implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getCurrentId() {
        return id;
    }

    public StationInRepository(String station) {
        this.station = station;
    }

    @Column(name = "station", nullable = false, unique = true)
    private String station;
}
