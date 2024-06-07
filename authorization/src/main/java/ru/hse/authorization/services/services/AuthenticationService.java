package ru.hse.authorization.services.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hse.authorization.domain.JwtAuthenticationResponse;
import ru.hse.authorization.domain.SignUpRequest;
import ru.hse.authorization.domain.SignInRequest;
import ru.hse.authorization.services.dto.UserInService;
import ru.hse.authorization.services.exceptions.UserIsNotRegisteredException;
import ru.hse.authorization.services.filter.UserChainHandler;
import ru.hse.authorization.services.filter.UserChainHandlerStarter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final SessionService sessionService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String signUp(SignUpRequest request) {

        UserChainHandler userChainHandler = new UserChainHandlerStarter();
        userChainHandler.handle(request);

        var user = UserInService.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .build();


        var savedUser = userService.create(user);

        var jwt = jwtService.generateToken(user);

        sessionService.save(savedUser, jwt);

        return jwt;
    }

    public String signIn(SignInRequest request) {

        var user = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(user);

        return jwt;

    }

    public UserInService getCurrentUser() throws UserIsNotRegisteredException {
        return userService.getCurrentUserByEmail();
    }
}