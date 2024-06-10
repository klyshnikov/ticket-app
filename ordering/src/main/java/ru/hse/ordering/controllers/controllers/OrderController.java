package ru.hse.ordering.controllers.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.authorization.services.api.AuthenticationService;
import ru.hse.authorization.services.exceptions.UserIsNotRegisteredException;
import ru.hse.ordering.controllers.api.OrderApi;
import ru.hse.ordering.services.api.OrderService;
import ru.hse.ordering.services.api.StationService;
import ru.hse.ordering.services.dto.StationInService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController implements OrderApi {
    private final OrderService orderService;
    private final StationService stationService;
    private final AuthenticationService authenticationService;

    public OrderController(
            OrderService orderService,
            StationService stationService,
            AuthenticationService authenticationService) {
        this.orderService = orderService;
        this.stationService = stationService;
        this.authenticationService = authenticationService;
    }

    @Override
    @PostMapping("/add-station")
    @Operation(summary = "Добавить станцию (при необходимости)")
    public ResponseEntity<String> addStation(@RequestParam String stationName) {
        stationService.add(stationName);
        return ResponseEntity.ok("Добавлено!");
    }

    @Override
    @GetMapping("/get-all-stations")
    @Operation(summary = "Получить список всех существующих станций")
    public ResponseEntity<List<StationInService>> getAllStations() {
        return ResponseEntity.ok(stationService.getAll());
    }

    @Override
    @PostMapping("/make-order")
    @Operation(summary = "Оформить билет")
    public ResponseEntity<String> makeOrder(
            @RequestParam String stationFrom,
            @RequestParam String stationTo
    ) {
        try {
            //Long currentUserId = authenticationService.getCurrentUser().getCurrentId();
            orderService.makeOrder(stationFrom, stationTo);
            return ResponseEntity.ok("Ваш заказ успешно сделан. Ожидайте обарботки.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @Override
    @GetMapping("/get-my-orders")
    @Operation(summary = "Получить все билеты текущего пользователя")
    public ResponseEntity<String> getMyOrders() throws UserIsNotRegisteredException {
        return ResponseEntity.ok(orderService.getMyOrders());
    }

}
