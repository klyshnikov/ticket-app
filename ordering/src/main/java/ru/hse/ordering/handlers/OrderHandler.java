package ru.hse.ordering.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.hse.ordering.mappers.OrderMapper;
import ru.hse.ordering.services.services.OrderService;

@Service
@Slf4j
public class OrderHandler {
    private final OrderService orderService;
    private static final String CRON = "*/10 * * * * *";

    public OrderHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = CRON)
    public void handle() throws InterruptedException {
        System.out.println("Handle");
        Thread.sleep(10000);
        var nonInitializedOrders = orderService.getAll().stream().filter(order -> order.getStatus()==0).toList();
        if (nonInitializedOrders.isEmpty()) {
            return;
        }

        var orderToChange = nonInitializedOrders.get(0);

        orderToChange.setStatus(1);

        orderService.save(orderToChange);
    }
}
