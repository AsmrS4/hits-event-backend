package com.backend.event_service.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_gen")
    @SequenceGenerator(name = "event_gen", sequenceName = "event_seq", allocationSize = 1)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "location")
    private String location;
    @Column(name = "date", nullable = true)
    private LocalDateTime date;
    @Column(name = "deadline", nullable = true)
    private LocalDateTime deadline;
    @Column(name = "authorId", nullable = false)
    private Long authorId;
    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "createdAt", nullable = true)
    private LocalDateTime modifiedAt;
}
