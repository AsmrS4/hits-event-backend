package com.backend.event_service.dto.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String companyName;
    private LocalDateTime date;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
