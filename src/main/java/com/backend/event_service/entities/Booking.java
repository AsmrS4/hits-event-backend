package com.backend.event_service.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_gen")
    @SequenceGenerator(name = "booking_gen", sequenceName = "booking_seq", allocationSize = 1)
    private Long id;
    @Column(name = "eventId")
    private Long eventId;
    @Column(name = "userLogin")
    private String userLogin;
    @Column(name = "createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();
}
