package com.backend.event_service.controllers;

import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.dto.event.EventCreateDTO;
import com.backend.event_service.dto.event.EventEditDTO;
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
    @PostMapping("/manage")
    public ResponseEntity<?> createEvent(@RequestBody EventCreateDTO dto) {
        return ResponseEntity.ok(eventService.createEvent(dto));
    }
    @PutMapping("/manage/{eventId}")
    public ResponseEntity<?> editEvent(@PathVariable Long eventId, @RequestBody EventEditDTO dto) {
        return ResponseEntity.ok(eventService.editEvent(eventId, dto));
    }
    @DeleteMapping("/manage/{eventId}")
    public ResponseEntity<RequestResponse> deleteEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventService.deleteEvent(eventId));
    }
}
