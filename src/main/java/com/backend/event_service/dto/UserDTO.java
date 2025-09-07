package com.backend.event_service.dto;

import com.backend.event_service.enums.AccountStatus;
import com.backend.event_service.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private Long chatId;
    private String firstName;
    private String lastName;
    private String login;
    private UserRole role;
    private AccountStatus accountStatus;
    private LocalDateTime createTime;
}
