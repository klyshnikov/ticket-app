package ru.hse.authorization.services.filter;

import ru.hse.authorization.domain.SignUpRequest;
import ru.hse.authorization.services.dto.UserInService;

public interface UserChainHandler {
    UserChainHandler getNextHandler();
    void handle(SignUpRequest userInService);
}
