package dev.blazo.billify.user_events.entities;

import dev.blazo.billify.events.entities.Event;
import dev.blazo.billify.users.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/27/2025
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_events")
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Size(max = 100, message = "UserEvent device must be at most 100 characters")
    @Column()
    private String device;

    @Size(max = 100, message = "UserEvent ip address must be at most 100 characters")
    @Column()
    private String ipAddress;

    /**
     * Timestamp of when the user event was created.
     * This field is automatically set to the current date and time when the entity is persisted.
     * It cannot be updated after it is set.
     */
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)  // Defines the field as a timestamp in the database
    private LocalDateTime createdAt;

    /**
     * Sets the creation date of the user account before persisting the entity.
     * This method ensures the 'createdAt' field is automatically set before the entity is saved in the database.
     * It is only set if it has not been manually assigned.
     */
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();  // Set the creation date if not already set
        }
    }
}
