package ru.hse.ordering.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hse.ordering.repository.dto.StationInRepository;
import ru.hse.ordering.repository.repository.StationRepository;

@Configuration
@RequiredArgsConstructor
public class OrderConfiguration {
    @Bean
    public CommandLineRunner commandLineRunner(StationRepository stationRepository) {
        return args -> {
            stationRepository.save(new StationInRepository("Moscow"));
            stationRepository.save(new StationInRepository("St. Petersburg"));
            stationRepository.save(new StationInRepository("Ekaterinburg"));
            stationRepository.save(new StationInRepository("Tumen"));
            stationRepository.save(new StationInRepository("Kurgan"));
            stationRepository.save(new StationInRepository("Chelabinsk"));
            stationRepository.save(new StationInRepository("Kazan"));
        };
    }
}
