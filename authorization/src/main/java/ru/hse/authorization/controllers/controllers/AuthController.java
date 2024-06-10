package ru.hse.authorization.controllers.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.authorization.controllers.api.AuthApi;
import ru.hse.authorization.domain.SignInRequest;
import ru.hse.authorization.domain.SignUpRequest;
import ru.hse.authorization.services.api.AuthenticationService;
import ru.hse.authorization.services.api.SessionService;
import ru.hse.authorization.services.api.UserService;
import ru.hse.authorization.services.dto.UserInService;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthApi {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final SessionService sessionService;

    public AuthController(
            AuthenticationService authenticationService,
            UserService userService,
            SessionService sessionService
    ) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    @Override
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.signUp(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    @Override
    public ResponseEntity<String> signIn(@RequestBody @Valid SignInRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.signIn(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    @GetMapping("get-user-info")
    @Operation(summary = "Получить информацию о текущем пользователе")
    public ResponseEntity<String> getUserInfo() {
        try {
            var currentUser = authenticationService.getCurrentUser();
            var currentSession = sessionService.getLastSessionByUserId(currentUser.getCurrentId());
            return ResponseEntity.ok(
                    "Пользователь \n" +
                            "Имя: " + currentUser.getNickname() + "\n" +
                            "Логин: " + currentUser.getEmail() + "\n" +
                            "Токен: " + currentSession.getTocken()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    @GetMapping("/get-all")
    @Operation(summary = "ТЕСТОВЫЙ МЕТОД ДЛЯ УДОБСТВА ТЕСТИРОВАНИЯ." +
            " Получить информацию о всех пользователях")
    public List<UserInService> getAll() {
        return userService.getAllUsers();
    }
}

