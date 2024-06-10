package ru.hse.authorization.services.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.authorization.mappers.SessionMapper;
import ru.hse.authorization.repository.dto.SessionInRepository;
import ru.hse.authorization.repository.repository.SessionRepository;
import ru.hse.authorization.services.api.SessionService;
import ru.hse.authorization.services.dto.SessionInService;
import ru.hse.authorization.services.dto.UserInService;
import ru.hse.authorization.services.exceptions.SessionIsNotExistedException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

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
                                Timestamp.valueOf(LocalDateTime.now().plus(Period.ofYears(1)))
                        )
                )
        );
    }

    @Override
    public SessionInService getLastSessionByUserId(Long user_id) throws SessionIsNotExistedException {
        SessionMapper mapper = new SessionMapper();
        List<SessionInRepository> sessions = sessionRepository.findAllByUserId(user_id);
        if (sessions.isEmpty()) {
            throw new SessionIsNotExistedException("Сеанса не существует для текущего пользователя");
        }
        sessions.sort((session1, session2) -> session2.getExpires().compareTo(session1.getExpires()));
        return mapper.SessionInRepositoryToSessionInService(sessions.getFirst());
    }
}
