package com.backend.event_service.services.event;

import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.dto.event.EventCreateDTO;
import com.backend.event_service.dto.event.EventDTO;
import com.backend.event_service.dto.event.EventEditDTO;
import com.backend.event_service.entities.Event;
import com.backend.event_service.entities.User;
import com.backend.event_service.enums.UserRole;
import com.backend.event_service.repositories.EventRepository;
import com.backend.event_service.repositories.UserRepository;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    @Override
    public List<EventDTO> getEvents() {
        return eventRepository.getActiveEvents(LocalDateTime.now()).stream().map(event -> {
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

    @Override
    public RequestResponse createEvent() {
        /*String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByLogin(userLogin)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        Event event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setDate(dto.getDate());
        event.setDeadline(dto.getDeadline());
        event.setCompanyName(user.getCompanyName());
        eventRepository.save(event);*/
        log.info("Saved");
        return new RequestResponse(true, 200, "New event added to database");
    }

    @Override
    public RequestResponse editEvent(Long eventId, EventEditDTO dto) {
        return null;
    }

    @Override
    public RequestResponse deleteEvent(Long eventId) {
        return null;
    }
}
