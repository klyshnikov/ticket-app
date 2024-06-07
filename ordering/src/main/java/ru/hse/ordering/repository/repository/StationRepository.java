package ru.hse.ordering.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.authorization.repository.dto.UserInRepository;
import ru.hse.ordering.repository.dto.StationInRepository;

import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<StationInRepository, Long> {
    List<StationInRepository> findAll();
    Optional<StationInRepository> findByStation(String station);
    List<StationInRepository> getAllById(Long id);
}
