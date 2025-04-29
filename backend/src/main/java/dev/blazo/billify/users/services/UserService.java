package dev.blazo.billify.users.services;

import dev.blazo.billify.common.email.EmailService;
import dev.blazo.billify.common.exception.ApiException;
import dev.blazo.billify.roles.entities.Role;
import dev.blazo.billify.roles.services.RoleService;
import dev.blazo.billify.user_roles.entities.UserRole;
import dev.blazo.billify.user_roles.services.UserRoleService;
import dev.blazo.billify.users.entities.User;
import dev.blazo.billify.users.repositories.UserRepository;
import dev.blazo.billify.verifications.account_verifications.entities.AccountVerification;
import dev.blazo.billify.verifications.account_verifications.services.AccountVerificationService;
import dev.blazo.billify.verifications.services.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static dev.blazo.billify.roles.enums.RoleTypes.ROLE_USER;
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
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final VerificationService verificationService;
    private final AccountVerificationService accountVerificationService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;

    /**
     * Constructs a new UserService instance.
     * This constructor is used to initialize the UserService with the required repositories
     * for managing users, user_roles, and roles.
     *
     * @param userRepository  the repository for performing CRUD operations and custom queries for User entities
     * @param userRoleService the service for managing UserRole entities
     * @param roleService     the repository for performing operations and queries on Role entities
     */
    @Autowired
    public UserService(UserRepository userRepository, UserRoleService userRoleService, VerificationService verificationService, AccountVerificationService accountVerificationService, RoleService roleService, BCryptPasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.verificationService = verificationService;
        this.accountVerificationService = accountVerificationService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
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

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            assignRoleToUser(user, ROLE_USER.name());

            String verificationUrl = verificationService.createVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());

            createAndSaveAccountVerification(user, verificationUrl);

            emailService.sendVerificationUrlEmail(user, verificationUrl, ACCOUNT);

            return userRepository.save(user);

        } catch (Exception e) {
            throw new ApiException("An error occurred while creating the user: " + e.getMessage());
        }
    }

    /**
     * Assigns a specific role to a user by creating and saving a UserRole entity.
     * The method retrieves the role by its name and associates it with the given user.
     *
     * @param user     the User entity to which the role will be assigned
     * @param roleName the name of the role to be assigned to the user
     * @throws ApiException if there is an error during the role assignment process
     */
    private void assignRoleToUser(User user, String roleName) throws ApiException {
        try {
            Role role = roleService.findByName(roleName);

            UserRole userRole = UserRole.builder().user(user).role(role).build();

            userRoleService.save(userRole);
        } catch (Exception e) {
            throw new ApiException("Failed to assign default user role: " + e.getMessage());
        }
    }

    /**
     * Creates and saves an AccountVerification entity for the given user and verification URL.
     *
     * @param user            the user to associate with the verification
     * @param verificationUrl the URL to be saved in the verification entity
     * @throws ApiException if saving, the account verification fails
     */
    private void createAndSaveAccountVerification(User user, String verificationUrl) {
        try {
            AccountVerification accountVerification = AccountVerification.builder().user(user).url(verificationUrl).build();

            accountVerificationService.save(accountVerification);
        } catch (Exception e) {
            throw new ApiException("Failed to create account verification: " + e.getMessage());
        }
    }

}
