package ru.hse.authorization.mappers;
import ru.hse.authorization.repository.dto.UserInRepository;
import ru.hse.authorization.services.dto.UserInService;

public class UserMapper {
    public UserInRepository ServiceUserToRepositoryUser(UserInService userInService) {
        return new UserInRepository(
                userInService.getNickname(),
                userInService.getEmail(),
                userInService.getPassword(),
                userInService.getTimestamp());
    }

    public UserInService RepositoryUserToServiceUser(UserInRepository userInRepository) {
        return new UserInService(
                userInRepository.getCurrentId(),
                userInRepository.getNickname(),
                userInRepository.getEmail(),
                userInRepository.getPassword(),
                userInRepository.getTimestamp());
    }
}
