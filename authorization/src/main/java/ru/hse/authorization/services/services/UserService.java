package ru.hse.authorization.services.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.hse.authorization.mappers.UserMapper;
import ru.hse.authorization.repository.dto.UserInRepository;
import ru.hse.authorization.repository.repository.UserRepository;
import ru.hse.authorization.services.dto.UserInService;

import java.util.ArrayList;
import java.util.List;
//import ru.minusd.security.domain.model.Role;
//import ru.minusd.security.domain.model.User;
//import ru.minusd.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    public UserInService save(UserInService user) {
        UserMapper mapper = new UserMapper();
        return mapper.RepositoryUserToServiceUser(repository.save(mapper.ServiceUserToRepositoryUser(user)));
    }


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    public UserInService create(UserInService user) {

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public UserInService getByUsername(String username) {
        UserMapper mapper = new UserMapper();
        return mapper.RepositoryUserToServiceUser(repository.findByNickname(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден")));

    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public UserInService getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public List<UserInService> getAllUsers() {
        UserMapper mapper = new UserMapper();

        List<UserInService> userInServiceList = new ArrayList<UserInService>();

        List<UserInRepository> allUsers = repository.findAll();

        for (var obj : allUsers) {
            userInServiceList.add(mapper.RepositoryUserToServiceUser(obj));
        }

        return userInServiceList;
    }


}

