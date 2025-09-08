package com.backend.event_service.repositories.user;

import com.backend.event_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String username);
    boolean existsByLogin(String username);

    @Query("SELECT u FROM User u WHERE u.accountStatus = 0")
    List<User> getUnverifiedAccounts();
}
