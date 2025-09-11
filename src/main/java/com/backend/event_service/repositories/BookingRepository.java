package com.backend.event_service.repositories;

import com.backend.event_service.dto.BookingDTO;
import com.backend.event_service.dto.UserGuestDTO;
import com.backend.event_service.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT b from Booking b WHERE b.eventId = :eventId AND b.userLogin = :userLogin ORDER BY b.id LIMIT 1")
    List<Booking> findAllBooking(@Param("eventId") Long eventId, @Param("userLogin") String userLogin);
    @Query("SELECT new com.backend.event_service.dto.UserGuestDTO(u.id, u.firstName, u.lastName, u.login) FROM User u JOIN Booking b ON u.login = b.userLogin WHERE b.eventId = :eventId")
    List<UserGuestDTO> findEventGuests(@Param("eventId") Long eventId);
    @Query("SELECT new com.backend.event_service.dto.BookingDTO(b.id, e.id, e.title, e.location, e.companyName, e.date) FROM Event e JOIN Booking b ON e.id = b.eventId WHERE b.userLogin = :userLogin")
    List<BookingDTO> getUsersBooking(@Param("userLogin") String userLogin);
}
