package ru.hse.ordering.services.dto;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;



@RequiredArgsConstructor
public class StationInService  {


    private Long id;

    public Long currentId() {
        return id;
    }

    public StationInService(Long id, String station) {
        this.station = station;
    }


    public String station;
}
