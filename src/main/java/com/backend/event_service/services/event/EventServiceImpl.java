package com.backend.event_service.services.event;

import com.backend.event_service.dto.EventDTO;
import com.backend.event_service.dto.UserDTO;
import com.backend.event_service.entities.Event;
import com.backend.event_service.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    @Override
    public List<EventDTO> getEvents() {
        return eventRepository.findAll().stream().map(event -> {
            EventDTO dto = new EventDTO();
            dto.setId(event.getId());
            dto.setTitle(event.getTitle());
            dto.setDescription(event.getDescription());
            dto.setLocation(event.getLocation());
            dto.setCompanyName(event.getCompanyName());
            dto.setDate(event.getDate());
            dto.setDeadline(event.getDeadline());
            dto.setCreatedAt(event.getCreatedAt());
            dto.setModifiedAt(event.getModifiedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public EventDTO getEventDetails(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(()-> new UsernameNotFoundException("Event not found"));
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setCompanyName(event.getCompanyName());
        dto.setLocation(event.getLocation());
        dto.setDate(event.getDate());
        dto.setDeadline(event.getDeadline());
        dto.setCreatedAt(event.getCreatedAt());
        dto.setModifiedAt(event.getModifiedAt());
        return dto;
    }
}
