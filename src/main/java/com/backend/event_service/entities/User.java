package com.backend.event_service.entities;

import com.backend.event_service.enums.AccountStatus;
import com.backend.event_service.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.CollectionId;

import java.time.LocalDateTime;

@Data
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "chatId", nullable = true, unique = true)
    private Long chatId;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "login", unique = true)
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "role", nullable = true)
    private UserRole role = null;
    @Column(name = "accountStatus", nullable = false)
    private AccountStatus accountStatus = AccountStatus.PENDING;
    @Column(name = "createTime", nullable = false)
    private LocalDateTime createTime = LocalDateTime.now();
}
