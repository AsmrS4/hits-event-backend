package com.backend.event_service.services.event;

import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.dto.event.EventCreateDTO;
import com.backend.event_service.dto.event.EventDTO;
import com.backend.event_service.dto.event.EventEditDTO;
import com.backend.event_service.entities.Event;
import com.backend.event_service.entities.User;
import com.backend.event_service.errors.BadRequestException;
import com.backend.event_service.repositories.EventRepository;
import com.backend.event_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public RequestResponse createEvent(EventCreateDTO dto) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByLogin(userLogin)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        Event event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setDate(dto.getDate());
        event.setDeadline(dto.getDeadline());
        event.setAuthorId(user.getId());
        event.setCompanyName(user.getCompanyName());
        eventRepository.save(event);

        return new RequestResponse(true, 200, userLogin);
    }

    @Override
    public RequestResponse editEvent(Long eventId, EventEditDTO dto) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByLogin(userLogin)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(()-> new UsernameNotFoundException("Event not found"));
        if(!event.getAuthorId().equals(user.getId())) {
            throw new BadRequestException("Event could be edited only by author");
        }
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setLocation(dto.getLocation());
        event.setDate(dto.getDate());
        event.setDeadline(dto.getDeadline());
        event.setModifiedAt(LocalDateTime.now());
        eventRepository.save(event);

        return new RequestResponse(true, 200, "Event was updated");
    }

    @Override
    public RequestResponse deleteEvent(Long eventId) {
        String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByLogin(userLogin)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(()-> new UsernameNotFoundException("Event not found"));
        if(!event.getAuthorId().equals(user.getId())) {
            throw new BadRequestException("Event could be deleted only by author");
        }
        eventRepository.delete(event);
        return new RequestResponse(true, 200, "Event was deleted");
    }
}
