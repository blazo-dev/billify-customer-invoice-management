package dev.blazo.billify.events.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.blazo.billify.events.enums.EventType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/27/2025
 */

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)  // Only include non-null fields in the serialized JSON response
@Entity  // This defines the JPA entity, mapping to the 'events' table in the database
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Event type is required")
    @Size(max = 50, message = "Event type must be at most 50 characters")
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private EventType type;

    @NotEmpty(message = "Event description is required")
    @Size(max = 255, message = "Event description must be at most 255 characters")
    @Column(nullable = false)
    private String description;

}

