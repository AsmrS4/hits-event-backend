package com.backend.event_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInRequest {
    @Size(min = 5, max = 100, message = "User login length must be in range of 5 and 100 symbols")
    @NotBlank(message = "Login is required")
    private String login;
    @Size(min = 6, max = 100, message = "Password length must be in range of 6 and 100 symbols")
    @NotBlank(message = "Password is required")
    private String password;
}
