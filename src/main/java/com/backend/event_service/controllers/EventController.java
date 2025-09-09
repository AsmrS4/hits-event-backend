package com.backend.event_service.controllers;

import com.backend.event_service.services.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    @GetMapping
    public ResponseEntity<?> getEvents() {
        return ResponseEntity.ok(eventService.getEvents());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getEventDetails(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventDetails(id));
    }

    @GetMapping("/{id}/guests")
    public ResponseEntity<?> getEventGuestList(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventGuests(id));
    }
}
