package dev.blazo.billify.events.repositories;

import dev.blazo.billify.events.entities.Event;
import dev.blazo.billify.events.enums.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/27/2025
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findEventByType(EventType type);
}
