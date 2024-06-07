package ru.hse.authorization.controllers.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.hse.authorization.domain.SignInRequest;
import ru.hse.authorization.domain.SignUpRequest;
import ru.hse.authorization.services.dto.UserInService;

import java.util.List;

public interface AuthApi {
    ResponseEntity<String> signUp(SignUpRequest request);

    ResponseEntity<String> signIn(SignInRequest request);

    ResponseEntity<String> getUserInfo();

    List<UserInService> getAll();
}
