package dev.blazo.billify.user_roles.services;

import dev.blazo.billify.common.exceptions.ApiException;
import dev.blazo.billify.roles.entities.Role;
import dev.blazo.billify.roles.services.RoleService;
import dev.blazo.billify.user_roles.entities.UserRole;
import dev.blazo.billify.user_roles.repositories.UserRoleRepository;
import dev.blazo.billify.users.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for managing UserRole.
 * Provides business logic for handling user-role associations.
 *
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/27/2025
 */
@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final RoleService roleService;

    /**
     * Find a UserRole by userId.
     *
     * @param userId The ID of the user.
     * @return The UserRole if found, or null if not.
     */
    public UserRole findByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId).orElseThrow(() -> new ApiException("User role not found for userId: " + userId));
    }

    /**
     * Find a UserRole by user email.
     *
     * @param userEmail The email of the user.
     * @return The UserRole if found, or null if not.
     */
    public UserRole findByUserEmail(String userEmail) {
        return userRoleRepository.findByUserEmail(userEmail).orElseThrow(() -> new ApiException("User role not found for email: " + userEmail));
    }

    /**
     * Find a UserRole by roleId.
     *
     * @param roleId The ID of the role.
     * @return The UserRole if found, or null if not.
     */
    public UserRole findByRoleId(Long roleId) {
        return userRoleRepository.findByRoleId(roleId).orElseThrow(() -> new ApiException("User role not found for roleId: " + roleId));
    }

    /**
     * Save a new UserRole to the database.
     *
     * @param userRole The UserRole entity to save.
     */
    public void save(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    /**
     * Delete a UserRole by userId.
     *
     * @param userId The ID of the user whose UserRole is to be deleted.
     */
    public void deleteByUserId(Long userId) {
        userRoleRepository.findByUserId(userId).ifPresent(userRoleRepository::delete);
    }

    /**
     * Updates the role of a user by changing the associated roleId.
     *
     * @param userId  The ID of the user whose role will be updated.
     * @param newRole The new Role entity to assign.
     * @throws ApiException if the user role is not found or the update fails.
     */
    public void updateUserRole(Long userId, Role newRole) {
        UserRole userRole = findByUserId(userId);

        userRole.setRole(newRole);
        userRoleRepository.save(userRole);
    }

    /**
     * Assigns a specific role to a user by creating and saving a UserRole entity.
     * The method retrieves the role by its name and associates it with the given user.
     *
     * @param user     the User entity to which the role will be assigned
     * @param roleName the name of the role to be assigned to the user
     * @throws ApiException if there is an error during the role assignment process
     */
    public void assignRoleToUser(User user, String roleName) throws ApiException {
        try {
            Role role = roleService.findByName(roleName);

            UserRole userRole = UserRole.builder().user(user).role(role).build();

            save(userRole);
        } catch (Exception e) {
            throw new ApiException("Failed to assign default user role: " + e.getMessage());
        }
    }
}
