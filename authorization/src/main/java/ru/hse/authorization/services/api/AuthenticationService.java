package ru.hse.authorization.services.api;

import ru.hse.authorization.domain.SignInRequest;
import ru.hse.authorization.domain.SignUpRequest;
import ru.hse.authorization.services.dto.UserInService;
import ru.hse.authorization.services.exceptions.UserIsNotRegisteredException;

public interface AuthenticationService {
    String signUp(SignUpRequest request);

    String signIn(SignInRequest request);

    UserInService getCurrentUser() throws UserIsNotRegisteredException;
}
