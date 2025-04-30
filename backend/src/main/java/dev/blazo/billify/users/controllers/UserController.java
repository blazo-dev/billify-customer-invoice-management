package dev.blazo.billify.users.controllers;

import dev.blazo.billify.common.https.HttpResponse;
import dev.blazo.billify.users.dtos.UserDto;
import dev.blazo.billify.users.entities.User;
import dev.blazo.billify.users.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * UserController is responsible for handling user-related requests.
 * It provides endpoint(s) to manage user operations such as user registration.
 *
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/30/2025
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Registers a new user and creates an entry in the system.
     * Handles both successful and failed user creation scenarios.
     *
     * @param user The user details provided in the request body. Must be valid and meet all validation requirements.
     * @return A {@code ResponseEntity<HttpResponse>} containing:
     * - HTTP 201 status if the user creation is successful, along with the user data.
     * - HTTP 400 status if the user creation fails, along with an error message.
     */
    @PostMapping
    public ResponseEntity<HttpResponse> registerUser(@RequestBody @Valid User user) {
        try {
            UserDto userDto = userService.createUser(user);

            return ResponseEntity.created(
                    getUri()
            ).body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .data(of("user", userDto))
                            .message("User created successfully")
                            .status(CREATED)
                            .statusCode(CREATED.value())
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .reason(e.getMessage())
                            .message("User creation failed")
                            .status(BAD_REQUEST)
                            .statusCode(BAD_REQUEST.value())
                            .build()
            );
        }
    }


    /**
     * Constructs a URI for the specific user resource.
     * The URI is built based on the current application context path and a predefined
     * user-related route pattern with a placeholder for the user ID.
     *
     * @return a URI representing the user resource path
     */
    private URI getUri() {
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/<userId>").toUriString());
    }
}
