package com.backend.event_service.repositories;

import com.backend.event_service.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Modifying
    @Query("SELECT e FROM Event e WHERE e.authorId := authorId")
    List<Event> getEventsByAuthorId(@Param("authorId") Long authorId);
}
