package dev.blazo.billify.user_roles.services;

import dev.blazo.billify.user_roles.entities.UserRole;
import dev.blazo.billify.user_roles.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing UserRole.
 * Provides business logic for handling user-role associations.
 *
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/27/2025
 */
@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    /**
     * Find a UserRole by userId.
     *
     * @param userId The ID of the user.
     * @return The UserRole if found, or null if not.
     */
    public UserRole findByUserId(Long userId) {
        Optional<UserRole> userRole = userRoleRepository.findByUserId(userId);
        return userRole.orElse(null);
    }

    /**
     * Find a UserRole by roleId.
     *
     * @param roleId The ID of the role.
     * @return The UserRole if found, or null if not.
     */
    public UserRole findByRoleId(Long roleId) {
        Optional<UserRole> userRole = userRoleRepository.findByRoleId(roleId);
        return userRole.orElse(null);
    }

    /**
     * Find a UserRole by userId and roleId.
     *
     * @param userId The ID of the user.
     * @param roleId The ID of the role.
     * @return The UserRole if found, or null if not.
     */
    public UserRole findByUserIdAndRoleId(Long userId, Long roleId) {
        Optional<UserRole> userRole = userRoleRepository.findByUserIdAndRoleId(userId, roleId);
        return userRole.orElse(null);
    }

    /**
     * Save a new UserRole to the database.
     *
     * @param userRole The UserRole entity to save.
     * @return The saved UserRole entity.
     */
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    /**
     * Delete a UserRole by userId.
     *
     * @param userId The ID of the user whose UserRole is to be deleted.
     */
    public void deleteByUserId(Long userId) {
        userRoleRepository.findByUserId(userId)
                .ifPresent(userRoleRepository::delete);
    }
}
