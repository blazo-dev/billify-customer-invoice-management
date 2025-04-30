package dev.blazo.billify.roles.services;

import dev.blazo.billify.common.exceptions.ApiException;
import dev.blazo.billify.roles.entities.Role;
import dev.blazo.billify.roles.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/28/2025
 * <p>
 * Service class responsible for handling business logic related to roles.
 * Provides methods to retrieve role information and additional functionalities
 * related to role management.
 * <p>
 * This service interacts with the RoleRepository to perform data operations
 * and ensures that roles are handled correctly within the application.
 */

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    /**
     * Retrieves a Role entity by its name. If the role is not found, an ApiException is thrown.
     *
     * @param name the name of the role to be retrieved
     * @return the Role entity associated with the provided name
     * @throws ApiException if no Role with the specified name is found
     */
    public Role findByName(String name) {
        return this.roleRepository.findByName(name).orElseThrow(() -> new ApiException("Role not found"));
    }
}
