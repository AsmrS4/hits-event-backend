package com.backend.event_service.dto;

import com.backend.event_service.enums.AccountStatus;
import com.backend.event_service.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String image;
    private String email;
    private UserRole role;
    private AccountStatus accountStatus;
}
