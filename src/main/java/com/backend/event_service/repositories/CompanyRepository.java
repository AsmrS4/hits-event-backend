package com.backend.event_service.repositories;

import com.backend.event_service.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByName(String name);
    Optional<Company> findById(Long id);
}
