package dev.blazo.billify.users.services;

import dev.blazo.billify.common.emails.EmailService;
import dev.blazo.billify.common.exceptions.ApiException;
import dev.blazo.billify.user_roles.services.UserRoleService;
import dev.blazo.billify.users.dtos.UserDto;
import dev.blazo.billify.users.entities.User;
import dev.blazo.billify.users.repositories.UserRepository;
import dev.blazo.billify.verifications.account_verifications.services.AccountVerificationService;
import dev.blazo.billify.verifications.services.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static dev.blazo.billify.roles.enums.RoleTypes.ROLE_USER;
import static dev.blazo.billify.users.services.UserMapper.toDto;
import static dev.blazo.billify.verifications.enums.VerificationType.ACCOUNT;

/**
 * Service class to handle users-related business logic.
 * This class includes methods for creating new users and verifying email uniqueness.
 *
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/26/2025
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final VerificationService verificationService;
    private final AccountVerificationService accountVerificationService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;


    /**
     * Creates a new user, assigns a default role, and initiates account verification.
     * The method checks if the email is already associated with an account,
     * encodes the provided password, assigns the default role, generates a verification URL,
     * and sends a verification email to the user.
     *
     * @param user the User entity containing details of the user to be created
     * @return the newly created User entity
     * @throws ApiException if the email is already associated with an account,
     *                      or if an error occurs during user creation
     */
    public UserDto createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ApiException("Email address is already associated with an account. Please use a different email.");
        }

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            User savedUser = userRepository.save(user);

            userRoleService.assignRoleToUser(user, ROLE_USER.name());

            String verificationUrl = verificationService.createVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());

            accountVerificationService.createAndSaveAccountVerification(user, verificationUrl);

            emailService.sendVerificationUrlEmail(user, verificationUrl, ACCOUNT);

            return toDto(savedUser);

        } catch (Exception e) {
            throw new ApiException("An error occurred while creating the user: " + e.getMessage());
        }
    }

    /**
     * Retrieves a User entity by its unique identifier.
     * If no User with the given ID is found, this method returns null.
     *
     * @param userId the unique identifier of the User to be retrieved
     * @return the User entity if found, or null if no User exists with the given ID
     */
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ApiException("User not found for id: " + userId));
    }

    /**
     * Finds a User by their email address.
     *
     * @param email the email address of the user to be searched
     * @return the User if found, or null if not
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ApiException("User not found for email: " + email));
    }


}
