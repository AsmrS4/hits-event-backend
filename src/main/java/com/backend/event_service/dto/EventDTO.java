package com.backend.event_service.dto;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime date;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
