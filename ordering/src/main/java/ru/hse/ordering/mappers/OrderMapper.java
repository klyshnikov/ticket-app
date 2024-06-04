package ru.hse.ordering.mappers;

import ru.hse.ordering.repository.dto.OrderInRepository;
import ru.hse.ordering.services.dto.OrderInService;

public class OrderMapper {
    public OrderInService OrderInRepositoryToOrderInService(OrderInRepository orderInRepository) {
        return new OrderInService(
                orderInRepository.getCurrentId(),
                orderInRepository.user_id,
                orderInRepository.from_station_id,
                orderInRepository.to_station_id,
                orderInRepository.status,
                orderInRepository.created
        );
    }

    public OrderInRepository OrderInServiceToOrderInRepository(OrderInService orderInService) {
        return new OrderInRepository(
                orderInService.user_id,
                orderInService.from_station_id,
                orderInService.to_station_id,
                orderInService.status,
                orderInService.created
        );
    }
}
