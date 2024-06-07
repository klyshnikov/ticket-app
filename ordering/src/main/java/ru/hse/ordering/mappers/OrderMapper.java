package ru.hse.ordering.mappers;

import ru.hse.ordering.repository.dto.OrderInRepository;
import ru.hse.ordering.services.dto.OrderInService;

public class OrderMapper {
    public OrderInService OrderInRepositoryToOrderInService(OrderInRepository orderInRepository) {
        return new OrderInService(
                orderInRepository.getCurrentId(),
                orderInRepository.getUserId(),
                orderInRepository.getFrom_station_id(),
                orderInRepository.getTo_station_id(),
                orderInRepository.getStatus(),
                orderInRepository.getCreated()
        );
    }

    public OrderInRepository OrderInServiceToOrderInRepository(OrderInService orderInService) {
        return new OrderInRepository(
                orderInService.getUserId(),
                orderInService.getFrom_station_id(),
                orderInService.getTo_station_id(),
                orderInService.getStatus(),
                orderInService.getCreated()
        );
    }

    public OrderInRepository OrderInServiceToOrderInRepositoryDeep(OrderInService orderInService) {
        return new OrderInRepository(
                orderInService.getCurrentId(),
                orderInService.getUserId(),
                orderInService.getFrom_station_id(),
                orderInService.getTo_station_id(),
                orderInService.getStatus(),
                orderInService.getCreated()
        );
    }
}
