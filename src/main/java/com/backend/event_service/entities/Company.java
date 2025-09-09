package com.backend.event_service.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_gen")
    @SequenceGenerator(name = "company_gen", sequenceName = "company_seq", allocationSize = 1)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();
}
