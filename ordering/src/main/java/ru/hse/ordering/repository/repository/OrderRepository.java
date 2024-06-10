package ru.hse.ordering.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.ordering.repository.dto.OrderInRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderInRepository, Long> {
    List<OrderInRepository> findAll();
    List<OrderInRepository> findAllByUserId(Long user_id);

}
