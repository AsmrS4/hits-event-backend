package com.backend.event_service.services.booking;

import com.backend.event_service.dto.BookingDTO;
import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.dto.user.UserGuestDTO;
import com.backend.event_service.entities.Booking;
import com.backend.event_service.entities.Event;
import com.backend.event_service.errors.AuthException;
import com.backend.event_service.errors.BadRequestException;
import com.backend.event_service.repositories.BookingRepository;
import com.backend.event_service.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{
    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    @Override
    public RequestResponse bookEvent(Long eventId) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        if(login == null) {
            throw new AuthException("");
        }
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new UsernameNotFoundException("Event not found"));
        if(event.getDeadline()!=null && event.getDeadline().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("You can't book event after deadline");
        }
        if(event.getDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("This event has passed");
        }
        List<Booking> bookingList = bookingRepository.findAllBooking(eventId, login);
        if(!bookingList.isEmpty()) {
            throw new BadRequestException("You already has booking");
        }
        Booking booking = new Booking();
        booking.setEventId(eventId);
        booking.setUserLogin(login);
        bookingRepository.save(booking);
        return new RequestResponse(true, 200, "Created new booking");
    }

    @Override
    public RequestResponse cancelEventBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()-> new UsernameNotFoundException("Booking not found"));
        bookingRepository.delete(booking);
        return new RequestResponse(true, 200, "Booking was cancelled");
    }

    @Override
    public List<UserGuestDTO> getEventGuests(Long eventId) {
        if(!eventRepository.existsById(eventId)) {
            throw new UsernameNotFoundException("Event not found");
        }
        return bookingRepository.findEventGuests(eventId);
    }

    @Override
    public List<BookingDTO> getUserBookings() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        if(login == null) {
            throw new AuthException("");
        }
        return bookingRepository.getUsersBooking(login);
    }


}
