package ru.hse.ordering.services.services;

//import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hse.authorization.repository.repository.UserRepository;
import ru.hse.authorization.services.api.AuthenticationService;
import ru.hse.authorization.services.exceptions.UserIsNotRegisteredException;
import ru.hse.ordering.mappers.OrderMapper;
import ru.hse.ordering.mappers.StationMapper;
import ru.hse.ordering.repository.dto.OrderInRepository;
import ru.hse.ordering.repository.dto.StationInRepository;
import ru.hse.ordering.repository.repository.OrderRepository;
import ru.hse.ordering.repository.repository.StationRepository;
import ru.hse.ordering.services.api.OrderService;
import ru.hse.ordering.services.dto.OrderInService;
import ru.hse.ordering.services.exceptions.EqualStationsException;
import ru.hse.ordering.services.exceptions.StationIsNotFoundException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final StationRepository stationRepository;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @Autowired
    public OrderServiceImpl(
            OrderRepository orderRepository,
            StationRepository stationRepository,
            AuthenticationService authenticationService,
            UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.stationRepository = stationRepository;
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @Override
    public List<OrderInService> getAll() {
        OrderMapper mapper = new OrderMapper();
        List<OrderInRepository> orderInRepositoryList = orderRepository.findAll();
        List<OrderInService> orderInServiceList = new ArrayList<OrderInService>();

        for (var order : orderInRepositoryList) {
            orderInServiceList.add(mapper.OrderInRepositoryToOrderInService(order));
        }

        return orderInServiceList;
    }

    @Override
    public void save(OrderInService orderInService) {
        OrderMapper mapper = new OrderMapper();

        orderRepository.save(mapper.OrderInServiceToOrderInRepositoryDeep(orderInService));
    }

    @Override
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
                        () -> new StationIsNotFoundException("Станция назначения не найдена. Проверьте данные")
                );

        Long currentUserId = authenticationService.getCurrentUser().getCurrentId();


        OrderInRepository order = new OrderInRepository(
                currentUserId,
                stationInRepositoryFrom.getCurrentId(),
                stationInRepositoryTo.getCurrentId(),
                0,
                Timestamp.valueOf(LocalDateTime.now())
        );

        orderRepository.save(order);
    }

    @Override
    public String getMyOrders() throws UserIsNotRegisteredException {
        Long currentUserId = authenticationService.getCurrentUser().getCurrentId();
        var orders = orderRepository.findAllByUserId(currentUserId);

        if (orders.isEmpty()) {
            return "У вас нет билетов.";
        }

        StringBuilder result = new StringBuilder("Ваши билеты: \n");
        for (var order : orders) {
            result.append("\nИдентификатор: " + order.getCurrentId() + "\n");
            result.append("Отправление: " + stationRepository.getAllById(order.getFrom_station_id()).get(0).getStation() + "\n");
            result.append("Прибытие: " + stationRepository.getAllById(order.getTo_station_id()).get(0).getStation() + "\n");
            if (order.getStatus() == 0) {
                result.append("Статус еще не назначен, ожидайте. \n");
            } else if (order.getStatus() == 1) {
                result.append("Статус: Билет исправен \n");
            } else {
                result.append("Статус: Блилет отменен и не может быть выдан. \n");
            }
        }

        return result.toString();
    }
}
