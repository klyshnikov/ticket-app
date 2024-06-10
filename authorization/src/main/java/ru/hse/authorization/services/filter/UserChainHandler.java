package ru.hse.authorization.services.filter;

import ru.hse.authorization.domain.SignUpRequest;

public interface UserChainHandler {
    UserChainHandler getNextHandler();
    void handle(SignUpRequest userInService);
}
