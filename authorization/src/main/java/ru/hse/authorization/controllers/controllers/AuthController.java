package ru.hse.authorization.controllers.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.authorization.domain.JwtAuthenticationResponse;
import ru.hse.authorization.domain.SignInRequest;
import ru.hse.authorization.domain.SignUpRequest;
import ru.hse.authorization.repository.dto.UserInRepository;
import ru.hse.authorization.repository.repository.UserRepository;
import ru.hse.authorization.services.dto.UserInService;
import ru.hse.authorization.services.services.AuthenticationService;
import ru.hse.authorization.services.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody @Valid SignUpRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.signUp(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new JwtAuthenticationResponse(e.getMessage()));
        }

    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody @Valid SignInRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.signIn(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new JwtAuthenticationResponse(e.getMessage()));
        }
    }

    @GetMapping("/get-all")
    public List<UserInService> getAll() {
        return userService.getAllUsers();
    }
}

