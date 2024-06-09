package ru.hse.ordering.services.api;

import ru.hse.authorization.services.exceptions.UserIsNotRegisteredException;
import ru.hse.ordering.services.dto.OrderInService;

import java.util.List;

public interface OrderService {

    List<OrderInService> getAll();

    void save(OrderInService orderInService);

    void makeOrder(String stationFrom, String stationTo) throws Exception;

    String getMyOrders() throws UserIsNotRegisteredException;

}
