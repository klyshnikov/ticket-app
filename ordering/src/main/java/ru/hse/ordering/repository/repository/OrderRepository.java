package ru.hse.ordering.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.ordering.repository.dto.OrderInRepository;
import ru.hse.ordering.repository.dto.StationInRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderInRepository, Long> {
    List<OrderInRepository> findAll();
}
