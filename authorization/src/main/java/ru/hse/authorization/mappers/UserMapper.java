package ru.hse.authorization.mappers;
import ru.hse.authorization.repository.dto.UserInRepository;
import ru.hse.authorization.services.dto.UserInService;

public class UserMapper {
    public UserInRepository ServiceUserToRepositoryUser(UserInService userInService) {
        return new UserInRepository(
                userInService.nickname,
                userInService.email,
                userInService.password,
                userInService.timestamp);
    }

    public UserInService RepositoryUserToServiceUser(UserInRepository userInRepository) {
        return new UserInService(
                userInRepository.getCurrentId(),
                userInRepository.nickname,
                userInRepository.email,
                userInRepository.password,
                userInRepository.timestamp);
    }
}
