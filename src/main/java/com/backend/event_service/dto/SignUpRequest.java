package com.backend.event_service.dto;

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
}
