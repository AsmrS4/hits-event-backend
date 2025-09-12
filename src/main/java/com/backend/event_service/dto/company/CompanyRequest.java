package com.backend.event_service.dto.company;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyRequest {
    @NotBlank(message = "Name field is required")
    private String name;
}
