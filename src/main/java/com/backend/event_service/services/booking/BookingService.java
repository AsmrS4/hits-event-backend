package com.backend.event_service.services.booking;

import com.backend.event_service.dto.*;
import com.backend.event_service.dto.user.UserGuestDTO;

import java.util.List;

public interface BookingService {
    RequestResponse bookEvent(Long eventId);
    RequestResponse cancelEventBooking(Long id);
    List<UserGuestDTO> getEventGuests(Long eventId);
    List<BookingDTO> getUserBookings();
}
