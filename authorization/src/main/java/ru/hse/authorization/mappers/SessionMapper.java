package ru.hse.authorization.mappers;

import ru.hse.authorization.repository.dto.SessionInRepository;
import ru.hse.authorization.services.dto.SessionInService;

public class SessionMapper {
    public SessionInRepository SessionInServiceToSessionInRepository(
            SessionInService sessionInService
    ) {
        return new SessionInRepository(
                sessionInService.user_id,
                sessionInService.tocken,
                sessionInService.expires
        );
    }

    public SessionInService SessionInRepositoryToSessionInService(
            SessionInRepository sessionInRepository
    ) {
        return new SessionInService(
                sessionInRepository.getCurrentId(),
                sessionInRepository.user_id,
                sessionInRepository.tocken,
                sessionInRepository.expires);
    }
}
