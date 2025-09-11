package com.backend.event_service.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEditDTO {
    @Size(min = 3, max = 50, message = "First name length must be in range of 5 and 50 symbols")
    @NotBlank(message = "First name is required")
    private String firstName;
    @Size(min = 3, max = 100, message = "Last name length must be in range of 5 and 100 symbols")
    @NotBlank(message = "Last name is required")
    private String lastName;
}
