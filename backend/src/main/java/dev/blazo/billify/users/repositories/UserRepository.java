package dev.blazo.billify.users.repositories;

import dev.blazo.billify.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for {@link User} entities.
 * Provides basic CRUD operations and custom queries methods.
 * Extends JpaRepository to leverage built-in methods.
 *
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 04/25/2025
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Custom queries to find a user by email.
     *
     * @param email the email to search for
     * @return an Optional of the users if found, otherwise an empty Optional
     */
    Optional<User> findByEmail(String email);

    /**
     * Custom queries to check if a user exists by their email.
     *
     * @param email the email to check
     * @return {@code true} if a users exists with the given email, otherwise {@code false}
     */
    Boolean existsByEmail(String email);
}
