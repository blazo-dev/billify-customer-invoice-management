package dev.blazo.billify.users.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) representing a User.
 * This class is used for transferring user-related data between different layers of the application.
 * It does not contain any business logic and is primarily used for encapsulating user data.
 *w
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/30/2025
 */
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private String title;
    private String bio;
    private String imageUrl;
    private Boolean isEnabled;
    private Boolean isUsingMfa;
    private Boolean isNotLocked;
    private LocalDateTime createdAt;
}
