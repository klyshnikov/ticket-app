package ru.hse.ordering.mappers;

import ru.hse.ordering.repository.dto.StationInRepository;
import ru.hse.ordering.services.dto.StationInService;

public class StationMapper {
    public StationInRepository StationInServiceToStationInRepository(StationInService stationInService) {
        return new StationInRepository(stationInService.getStation());
    }

    public StationInService StationInRepositoryToStationInService(StationInRepository stationInRepository) {
        return new StationInService(
                stationInRepository.getCurrentId(),
                stationInRepository.getStation()
        );
    }
}
