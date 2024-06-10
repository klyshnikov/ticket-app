package ru.hse.authorization.mappers;

import ru.hse.authorization.repository.dto.SessionInRepository;
import ru.hse.authorization.services.dto.SessionInService;

public class SessionMapper {
    public SessionInRepository SessionInServiceToSessionInRepository(
            SessionInService sessionInService
    ) {
        return new SessionInRepository(
                sessionInService.getUser_id(),
                sessionInService.getTocken(),
                sessionInService.getExpires()
        );
    }

    public SessionInService SessionInRepositoryToSessionInService(
            SessionInRepository sessionInRepository
    ) {
        return new SessionInService(
                sessionInRepository.getCurrentId(),
                sessionInRepository.getUserId(),
                sessionInRepository.getTocken(),
                sessionInRepository.getExpires());
    }
}
