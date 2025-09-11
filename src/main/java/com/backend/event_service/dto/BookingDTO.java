package com.backend.event_service.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BookingDTO {
    private Long id;
    private Long eventId;
    private String title;
    private String location;
    private String companyName;
    private LocalDateTime date;
}
