package com.backend.event_service.dto;

import com.backend.event_service.enums.UserRole;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {
    @Size(min = 5, max = 50, message = "First name length must be in range of 5 and 50 symbols")
    @NotBlank(message = "First name is required")
    private String firstName;
    @Size(min = 5, max = 100, message = "Last name length must be in range of 5 and 100 symbols")
    @NotBlank(message = "Last name is required")
    private String lastName;
    @Size(min = 5, max = 100, message = "User login length must be in range of 5 and 100 symbols")
    @NotBlank(message = "Login is required")
    private String login;
    @NotBlank(message = "Role is required")
    private UserRole role;
    @Size(min = 6, max = 100, message = "Password length must be in range of 6 and 100 symbols")
    @NotBlank(message = "Password is required")
    private String password;
    @Nullable
    private String companyName;
    @Nullable
    private Long chatId;
}
