package ru.hse.ordering.services.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hse.authorization.services.dto.UserInService;
import ru.hse.authorization.services.exceptions.UserIsNotRegisteredException;
import ru.hse.authorization.services.services.AuthenticationService;
import ru.hse.authorization.services.services.UserService;
import ru.hse.ordering.mappers.StationMapper;
import ru.hse.ordering.repository.dto.OrderInRepository;
import ru.hse.ordering.repository.dto.StationInRepository;
import ru.hse.ordering.repository.repository.OrderRepository;
import ru.hse.ordering.repository.repository.StationRepository;
import ru.hse.ordering.services.dto.OrderInService;
import ru.hse.ordering.services.dto.StationInService;
import ru.hse.ordering.services.exceptions.EqualStationsException;
import ru.hse.ordering.services.exceptions.StationIsNotFoundException;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final StationRepository stationRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public OrderService(
            OrderRepository orderRepository,
            StationRepository stationRepository,
            AuthenticationService authenticationService
    ) {
        this.orderRepository = orderRepository;
        this.stationRepository = stationRepository;
        this.authenticationService = authenticationService;
    }

    public void makeOrder(String stationFrom, String stationTo) throws Exception {
        StationMapper stationMapper = new StationMapper();

        if (stationFrom.equals(stationTo)) {
            throw new EqualStationsException("Станции не могут быть одинаковыми.");
        }

        StationInRepository stationInRepositoryFrom
                = stationRepository
                .findByStation(stationFrom)
                .orElseThrow(
                        () -> new StationIsNotFoundException("Станция отправления не найдена. Проверьте данные")
                );

        StationInRepository stationInRepositoryTo
                = stationRepository
                .findByStation(stationTo)
                .orElseThrow(
                        () -> new StationIsNotFoundException("Станция отправления не найдена. Проверьте данные")
                );

        Long currentUserId = authenticationService.getCurrentUser().getCurrentId();

        if (stationInRepositoryFrom.getCurrentId() == null) {
            throw new Exception("Kurwa");
        }

        if (stationInRepositoryTo.getCurrentId() == null) {
            throw new Exception("Kurwa");
        }

        OrderInRepository order = new OrderInRepository(
                currentUserId,
                stationInRepositoryFrom.getCurrentId(),
                stationInRepositoryTo.getCurrentId(),
                0,
                Timestamp.valueOf(LocalDateTime.now())
        );

        orderRepository.save(order);
    }
}
