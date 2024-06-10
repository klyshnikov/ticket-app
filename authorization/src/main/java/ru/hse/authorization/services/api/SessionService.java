package ru.hse.authorization.services.api;

import ru.hse.authorization.services.dto.SessionInService;
import ru.hse.authorization.services.dto.UserInService;
import ru.hse.authorization.services.exceptions.SessionIsNotExistedException;

public interface SessionService {
    SessionInService save(UserInService userInService, String jwtTocken);

    SessionInService getLastSessionByUserId(Long user_id) throws SessionIsNotExistedException;
}
