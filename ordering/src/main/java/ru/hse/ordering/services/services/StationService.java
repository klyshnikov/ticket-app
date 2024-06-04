package ru.hse.ordering.services.services;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hse.ordering.mappers.StationMapper;
import ru.hse.ordering.repository.dto.StationInRepository;
import ru.hse.ordering.repository.repository.StationRepository;
import ru.hse.ordering.services.dto.StationInService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class StationService {
    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public void add(StationInService stationInService) {
        StationMapper mapper = new StationMapper();
        stationRepository.save(mapper.StationInServiceToStationInRepository(stationInService));
    }

    public List<StationInService> getAll() {
        List<StationInRepository> stationInRepositoryList = stationRepository.findAll();

        List<StationInService> stationInServiceList = new ArrayList<StationInService>();

        StationMapper mapper = new StationMapper();

        for (var station : stationInRepositoryList) {
            stationInServiceList.add(mapper.StationInRepositoryToStationInService(station));
        }

        return stationInServiceList;
    }
}