package ru.hse.ordering.services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hse.ordering.mappers.StationMapper;
import ru.hse.ordering.repository.dto.StationInRepository;
import ru.hse.ordering.repository.repository.StationRepository;
import ru.hse.ordering.services.api.StationService;
import ru.hse.ordering.services.dto.StationInService;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationServiceImpl implements StationService {
    private final StationRepository stationRepository;

    @Autowired
    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public void add(String stationName) {
        StationMapper mapper = new StationMapper();
        stationRepository.save(mapper.StationInServiceToStationInRepository(new StationInService(stationName)));
    }

    @Override
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