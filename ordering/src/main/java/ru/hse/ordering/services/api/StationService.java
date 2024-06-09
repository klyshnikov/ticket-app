package ru.hse.ordering.services.api;

import ru.hse.ordering.services.dto.StationInService;

import java.util.List;

public interface StationService {
    void add(String stationName);

    List<StationInService> getAll();
}
