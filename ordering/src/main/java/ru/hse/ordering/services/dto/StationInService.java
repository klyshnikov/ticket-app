package ru.hse.ordering.services.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class StationInService  {

    public Long currentId() {
        return id;
    }

    public StationInService(String station) {
        this.station = station;
    }

    public StationInService(Long id, String station) {
        this.id = id;
        this.station = station;
    }

    private Long id;

    private String station;
}
