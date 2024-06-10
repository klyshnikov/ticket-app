package ru.hse.authorization.repository.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import ru.hse.authorization.repository.dto.UserInRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInRepository, Long> {
    List<UserInRepository> findAll();
    Optional<UserInRepository> findByNickname(String username);
    Optional<UserInRepository> findByEmail(String email);
    boolean existsByNickname(String username);
    boolean existsByEmail(String email);

}
