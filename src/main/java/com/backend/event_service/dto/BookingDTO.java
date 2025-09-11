package com.backend.event_service.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingDTO {
    private Long id;
    private Long eventId;
    private String userLogin;
    private LocalDateTime createdAt;
}
