package ru.hse.authorization.controllers.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.authorization.controllers.api.AuthApi;
import ru.hse.authorization.domain.SignInRequest;
import ru.hse.authorization.domain.SignUpRequest;
import ru.hse.authorization.services.api.AuthenticationService;
import ru.hse.authorization.services.api.UserService;
import ru.hse.authorization.services.dto.UserInService;
import ru.hse.authorization.services.services.AuthenticationServiceImpl;
import ru.hse.authorization.services.services.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthApi {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
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
    @GetMapping
    public ResponseEntity<String> getUserInfo() {
        try {
            var currentUser = authenticationService.getCurrentUser();
            return ResponseEntity.ok(
                    "Пользователь \n" +
                    "Имя: " + currentUser.getNickname() + "\n" +
                    "Логин: " + currentUser.getEmail()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    @GetMapping("/get-all")
    public List<UserInService> getAll() {
        return userService.getAllUsers();
    }
}

