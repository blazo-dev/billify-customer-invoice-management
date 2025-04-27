package dev.blazo.billify.users.services;

import dev.blazo.billify.common.exception.ApiException;
import dev.blazo.billify.users.entities.User;
import dev.blazo.billify.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class to handle users-related business logic.
 * This class includes methods for creating new users and verifying email uniqueness.
 *
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/26/2025
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * Constructor to initialize the UserService with a UserRepository.
     *
     * @param userRepository the UserRepository instance to be used in this service
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Logic for creating a new user.
     * This method checks if the provided email already exists in the repositories before saving the users.
     *
     * @param user the users to be created
     * @return the created users
     * @throws ApiException if the email is already associated with an account
     */
    public User createUser(User user) {
        // Check if the email is already registered
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ApiException("Email address is already associated with an account. Please use a different email.");
        }

        // If the email doesn't exist, save the new users
        return userRepository.save(user);
    }
}
