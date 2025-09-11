package com.backend.event_service.services.booking;

import com.backend.event_service.dto.BookingDTO;
import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.dto.UserDTO;
import com.backend.event_service.dto.UserGuestDTO;

import java.util.List;

public interface BookingService {
    RequestResponse bookEvent(Long eventId);
    RequestResponse cancelEventBooking(Long id);
    List<UserGuestDTO> getEventGuests(Long eventId);
    List<BookingDTO> getUserBookings();
}
