package com.backend.event_service.repositories.auth;

import com.backend.event_service.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<Token, Long> {
    boolean existsByToken(String token);
}
