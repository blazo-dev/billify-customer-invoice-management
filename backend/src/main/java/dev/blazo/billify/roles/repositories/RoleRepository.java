package dev.blazo.billify.roles.repositories;

import dev.blazo.billify.roles.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * RoleRepository extends JpaRepository to interact with the database for Role entities.
 * This repository provides basic CRUD operations and custom queries for Role management.
 *
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 04/26/2025
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a role by its name.
     *
     * @param name the name of the roles
     * @return an Optional containing the Role if found, otherwise empty
     */
    Optional<Role> findByName(String name);

    /**
     * Checks if a role with the given name exists.
     *
     * @param name the name of the roles
     * @return true if the role exists, false otherwise
     */
    boolean existsByName(String name);

}
