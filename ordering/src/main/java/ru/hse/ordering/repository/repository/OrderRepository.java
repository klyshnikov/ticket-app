package ru.hse.ordering.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.authorization.repository.dto.UserInRepository;
import ru.hse.ordering.repository.dto.OrderInRepository;
import ru.hse.ordering.repository.dto.StationInRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderInRepository, Long> {
    List<OrderInRepository> findAll();
    List<OrderInRepository> findAllByUserId(Long user_id);

}
