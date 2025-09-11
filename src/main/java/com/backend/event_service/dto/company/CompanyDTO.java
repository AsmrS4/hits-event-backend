package com.backend.event_service.dto.company;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CompanyDTO {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
}
