package dev.blazo.billify.verifications.reset_password_verifications.entities;

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
@Table(name = "reset_password_verifications")
public class ResetPasswordVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Size(max = 255, message = "Reset password verification url must be at most 255 characters")
    @Column(unique = true)
    private String url;

    @Column(nullable = false)
    private LocalDateTime expirationDate;
}
