package com.backend.event_service.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "blacklist")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blacklisted_gen")
    @SequenceGenerator(name = "blacklisted_gen", sequenceName = "blacklisted_seq", allocationSize = 1)
    private Long id;
    @Column(name = "token", nullable = false, unique = true)
    private String token;
}
