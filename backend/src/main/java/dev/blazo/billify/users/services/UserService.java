package dev.blazo.billify.users.services;

import dev.blazo.billify.common.exception.ApiException;
import dev.blazo.billify.roles.entities.Role;
import dev.blazo.billify.roles.repositories.RoleRepository;
import dev.blazo.billify.user_roles.entities.UserRole;
import dev.blazo.billify.user_roles.repositories.UserRoleRepository;
import dev.blazo.billify.users.entities.User;
import dev.blazo.billify.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static dev.blazo.billify.roles.enums.RoleTypes.ROLE_USER;

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
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Constructs a new UserService instance.
     * This constructor is used to initialize the UserService with the required repositories
     * for managing users, user_roles, and roles.
     *
     * @param userRepository     the repository for performing CRUD operations and custom queries for User entities
     * @param userRoleRepository the repository for managing UserRole entities
     * @param roleRepository     the repository for performing operations and queries on Role entities
     */
    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new user in the system after verifying that the email address is not already associated with an existing account.
     * Assigns the default user role to the newly created user.
     *
     * @param user the User object containing the user's details to be created
     * @return the newly created User object after being saved to the database
     * @throws ApiException if the email is already registered, or the required user role is not found
     */
    public User createUser(User user) {
        // Check if the email is already registered
        // This prevents duplicate user accounts by ensuring the email address is unique.
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ApiException("Email address is already associated with an account. Please use a different email.");
        }

        // Encrypt the user's password before saving to the database
        // This ensures that plain-text passwords are never stored.
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Retrieve the default role for a new user from the database
        // If the role is not found, an exception is thrown.
        Role role = roleRepository.findByName(ROLE_USER.name()).orElseThrow(
                () -> new ApiException("Role not found")
        );

        // Create a new UserRole entity linking the user and their assigned role
        // This establishes the relationship between the user and the default role.
        UserRole userRole = UserRole.builder()
                .user(user)
                .role(role)
                .build();

        // Save the UserRole association to the database
        userRoleRepository.save(userRole);

        // Finally, save the new user to the database and return the saved user object
        return userRepository.save(user);
    }
}
