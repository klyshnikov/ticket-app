package ru.hse.authorization.services.api;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.hse.authorization.services.dto.UserInService;
import ru.hse.authorization.services.exceptions.UserIsNotRegisteredException;

import java.util.List;

public interface UserService {
    UserInService save(UserInService user);

    UserInService create(UserInService user);

    UserInService getByUsername(String username);

    UserDetailsService userDetailsService();

    UserInService getCurrentUserByName();

    List<UserInService> getAllUsers();

    String getUserEmail() throws UserIsNotRegisteredException;

    UserInService getUserByEmail(String email);

    UserInService getCurrentUserByEmail() throws UserIsNotRegisteredException;

}
