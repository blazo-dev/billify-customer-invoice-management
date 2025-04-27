package dev.blazo.billify.user_events.repositories;

import dev.blazo.billify.user_events.entities.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/27/2025
 */
@Repository
public interface UserEventRepository extends JpaRepository<UserEvent, Long> {
}
