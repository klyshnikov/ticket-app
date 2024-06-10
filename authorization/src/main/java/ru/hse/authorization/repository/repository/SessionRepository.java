package ru.hse.authorization.repository.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hse.authorization.repository.dto.SessionInRepository;

import java.util.List;

public interface SessionRepository extends CrudRepository<SessionInRepository, Long> {
    List<SessionInRepository> findAllByUserId(Long user_id);
}
