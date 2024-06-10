package ru.hse.authorization.services.filter;

import ru.hse.authorization.domain.SignUpRequest;

public class UserChainHandlerStarter implements UserChainHandler {

    @Override
    public UserChainHandler getNextHandler() {
        return new EmailUserChainHandler();
    }

    @Override
    public void handle(SignUpRequest request) {
        getNextHandler().handle(request);
    }
}
