package com.backend.event_service.services.event;

import com.backend.event_service.dto.EventDTO;
import com.backend.event_service.dto.UserDTO;

import java.util.List;

public interface EventService {
    List<EventDTO> getEvents();
    EventDTO getEventDetails(Long eventId);
    List<UserDTO> getEventGuests(Long eventId);
}
