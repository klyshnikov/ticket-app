package ru.hse.ordering.controllers.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hse.authorization.services.exceptions.UserIsNotRegisteredException;
import ru.hse.ordering.services.dto.StationInService;

import java.util.List;

public interface OrderApi {
    ResponseEntity<String> addStation(@RequestParam String stationName);

    ResponseEntity<List<StationInService>> getAllStations();

    ResponseEntity<String> makeOrder(
            @RequestParam String stationFrom,
            @RequestParam String stationTo
    );

    ResponseEntity<String> getMyOrders() throws UserIsNotRegisteredException;
}
