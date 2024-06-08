package ru.hse.authorization.services.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.authorization.mappers.SessionMapper;
import ru.hse.authorization.repository.dto.SessionInRepository;
import ru.hse.authorization.repository.repository.SessionRepository;
import ru.hse.authorization.services.api.SessionService;
import ru.hse.authorization.services.dto.SessionInService;
import ru.hse.authorization.services.dto.UserInService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;

    @Override
    public SessionInService save(UserInService userInService, String jwtTocken) {

        SessionMapper mapper = new SessionMapper();

        return mapper.SessionInRepositoryToSessionInService(
                sessionRepository.save(
                        new SessionInRepository(
                                userInService.getCurrentId(),
                                jwtTocken,
                                Timestamp.valueOf(LocalDateTime.now())
                        )
                )
        );
    }
}
