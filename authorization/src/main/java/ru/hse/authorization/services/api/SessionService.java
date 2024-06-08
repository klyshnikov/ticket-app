package ru.hse.authorization.services.api;

import ru.hse.authorization.services.dto.SessionInService;
import ru.hse.authorization.services.dto.UserInService;

public interface SessionService {
    SessionInService save(UserInService userInService, String jwtTocken);
}
