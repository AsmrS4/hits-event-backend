package com.backend.event_service.controllers;

import com.backend.event_service.dto.BookingDTO;
import com.backend.event_service.services.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    @GetMapping("/guests/{eventId}")
    public ResponseEntity<?> getGuests(@PathVariable Long eventId) {
        return ResponseEntity.ok(bookingService.getEventGuests(eventId));
    }
    @PostMapping("/booking/{eventId}")
    public ResponseEntity<?> bookEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(bookingService.bookEvent(eventId));
    }

    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingService.cancelEventBooking(bookingId));
    }

    @GetMapping("/booking/my")
    public ResponseEntity<List<BookingDTO>> getUserBookings() {
        return ResponseEntity.ok(bookingService.getUserBookings());
    }

}
