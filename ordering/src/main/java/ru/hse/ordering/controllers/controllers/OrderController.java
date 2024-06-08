package ru.hse.ordering.controllers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.authorization.services.exceptions.UserIsNotRegisteredException;
import ru.hse.authorization.services.services.AuthenticationServiceImpl;
import ru.hse.ordering.services.dto.StationInService;
import ru.hse.ordering.services.services.OrderService;
import ru.hse.ordering.services.services.StationService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final StationService stationService;
    private final AuthenticationServiceImpl authenticationService;

    @Autowired
    public OrderController(
            OrderService orderService,
            StationService stationService,
            AuthenticationServiceImpl authenticationService) {
        this.orderService = orderService;
        this.stationService = stationService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/add-station")
    public ResponseEntity<String> addStation(@RequestParam String stationName) {
        stationService.add(stationName);
        return ResponseEntity.ok("Добавлено!");
    }

    @GetMapping("/get-all-stations")
    public ResponseEntity<List<StationInService>> getAllStations() {
        return ResponseEntity.ok(stationService.getAll());
    }
    
    @PostMapping("/make-order")
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

    @GetMapping("/get-my-orders")
    public ResponseEntity<String> getMyOrders() throws UserIsNotRegisteredException {
        return ResponseEntity.ok(orderService.getMyOrders());
    }

//    @GetMapping("/get-all-orders")
//    public ResponseEntity<List<OrderInService>> getAllOrders() {
//        return ResponseEntity.ok();
//    }
}
