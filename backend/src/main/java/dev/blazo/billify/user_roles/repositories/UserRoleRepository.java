package dev.blazo.billify.user_roles.repositories;

import dev.blazo.billify.user_roles.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing UserRole entities.
 * This interface provides CRUD operations for the UserRole table.
 *
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/27/2025
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    /**
     * Find a UserRole by its user ID.
     *
     * @param userId The ID of the user.
     * @return An Optional containing the UserRole if found, or empty if not.
     */
    Optional<UserRole> findByUserId(Long userId);

    /**
     * Find a UserRole by its role ID.
     *
     * @param roleId The ID of the role.
     * @return An Optional containing the UserRole if found, or empty if not.
     */
    Optional<UserRole> findByRoleId(Long roleId);

    /**
     * Find a UserRole by its user and role IDs.
     * This can be useful to check if a specific role is assigned to a specific user.
     *
     * @param userId The ID of the user.
     * @param roleId The ID of the role.
     * @return An Optional containing the UserRole if found, or empty if not.
     */
    Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId);
}
