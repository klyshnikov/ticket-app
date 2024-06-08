package ru.hse.authorization.services.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.hse.authorization.mappers.UserMapper;
import ru.hse.authorization.repository.dto.UserInRepository;
import ru.hse.authorization.repository.repository.UserRepository;
import ru.hse.authorization.services.api.UserService;
import ru.hse.authorization.services.dto.UserInService;
import ru.hse.authorization.services.exceptions.UserIsNotRegisteredException;

import java.util.ArrayList;
import java.util.List;
//import ru.minusd.security.domain.model.Role;
//import ru.minusd.security.domain.model.User;
//import ru.minusd.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public UserInService save(UserInService user) {
        UserMapper mapper = new UserMapper();
        return mapper.RepositoryUserToServiceUser(repository.save(mapper.ServiceUserToRepositoryUser(user)));
    }

    @Override
    public UserInService create(UserInService user) {

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }

    @Override
    public UserInService getByUsername(String username) {
        UserMapper mapper = new UserMapper();
        return mapper.RepositoryUserToServiceUser(repository.findByNickname(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден")));

    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    @Override
    public UserInService getCurrentUserByName() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Override
    public List<UserInService> getAllUsers() {
        UserMapper mapper = new UserMapper();

        List<UserInService> userInServiceList = new ArrayList<UserInService>();

        List<UserInRepository> allUsers = repository.findAll();

        for (var obj : allUsers) {
            userInServiceList.add(mapper.RepositoryUserToServiceUser(obj));
        }

        return userInServiceList;
    }

    @Override
    public String getUserEmail() throws UserIsNotRegisteredException {
        var currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser instanceof UserInService) {
            return ((UserInService) currentUser).getEmail();
        }

        throw new UserIsNotRegisteredException("На данный момент вы незарегистрированный пользователь");
    }

    @Override
    public UserInService getUserByEmail(String email) {
        UserMapper mapper = new UserMapper();
        return mapper.RepositoryUserToServiceUser(repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден")));
    }

    @Override
    public UserInService getCurrentUserByEmail() throws UserIsNotRegisteredException {
        return getUserByEmail(getUserEmail());
    }
}

