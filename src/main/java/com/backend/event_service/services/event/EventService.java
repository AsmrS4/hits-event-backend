package com.backend.event_service.services.event;

import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.dto.event.EventCreateDTO;
import com.backend.event_service.dto.event.EventDTO;
import com.backend.event_service.dto.event.EventEditDTO;
import jakarta.security.auth.message.AuthException;

import java.util.List;

public interface EventService {
    List<EventDTO> getEvents();
    EventDTO getEventDetails(Long eventId);
    RequestResponse createEvent();
    RequestResponse editEvent(Long eventId, EventEditDTO dto);
    RequestResponse deleteEvent(Long eventId);
}
