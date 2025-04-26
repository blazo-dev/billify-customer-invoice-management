package dev.blazo.billify.repository;

import dev.blazo.billify.domain.User;

import java.util.Collection;

/**
 * Generic interface for user repository operations.
 * Defines basic CRUD operations for managing user entities.
 * This interface can work with any class that is User or extends User, and all methods will use that specific type.
 *
 * @param <T> a type that extends {@link User}
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 04/25/2025
 */
public interface UserRepository<T extends User> {

    /**
     * Persists a new user in the repository.
     *
     * @param user the user entity to be created
     * @return the created user with any generated fields (e.g., ID)
     */
    T create(T user);

    /**
     * Retrieves a paginated list of users.
     *
     * @param page     the page number (starting from 1)
     * @param pageSize the number of users per page
     * @return a collection of users for the specified page
     */
    Collection<T> getAll(int page, int pageSize);

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the ID of the user
     * @return the user if found, otherwise {@code null}
     */
    T get(Long id);

    /**
     * Updates an existing user in the repository.
     *
     * @param user the user entity with updated fields
     * @return the updated user
     */
    T update(T user);

    /**
     * Deletes a user from the repository by their ID.
     *
     * @param id the ID of the user to delete
     * @return {@code true} if deletion was successful, otherwise {@code false}
     */
    Boolean delete(Long id);
}
