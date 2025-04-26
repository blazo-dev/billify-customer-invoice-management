package dev.blazo.billify.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
public class User {
    private Long id;

    @NotEmpty(message = "First name is required")
    @Size(max = 50, message = "First name must be at most 50 characters")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    private String lastName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be at most 100 characters")
    private String email;

    @Size(max = 255, message = "Password must be at most 255 characters")
    private String password;

    @Size(max = 255, message = "Address must be at most 255 characters")
    private String address;

    @Size(max = 30, message = "Phone must be at most 30 characters")
    private String phone;

    @Size(max = 50, message = "Title must be at most 50 characters")
    private String title;

    @Size(max = 255, message = "Bio must be at most 255 characters")
    private String bio;

    @Size(max = 255, message = "Image URL must be at most 255 characters")
    private String imageUrl;

    private boolean enabled;

    private boolean isNotLocked;

    private boolean useMfa;

    private LocalDateTime createdAt;
}
