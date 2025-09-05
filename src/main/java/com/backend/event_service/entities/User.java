package com.backend.event_service.entities;

import com.backend.event_service.enums.AccountStatus;
import com.backend.event_service.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "image")
    private String image = null;
    @Column(name = "email", unique = true)
    @Email(message = "Incorrect email format")
    private String email;
    @Column(name = "role", nullable = true)
    private UserRole role = null;
    @Column(name = "accountStatus", nullable = false)
    private AccountStatus accountStatus = AccountStatus.PENDING;
}
