package ru.hse.authorization.repository.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hse.authorization.repository.dto.SessionInRepository;
import ru.hse.authorization.repository.dto.UserInRepository;

public interface SessionRepository extends CrudRepository<SessionInRepository, Long> {

}
