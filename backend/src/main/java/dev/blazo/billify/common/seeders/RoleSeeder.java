package dev.blazo.billify.common.seeders;

import dev.blazo.billify.roles.entities.Role;
import dev.blazo.billify.roles.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static dev.blazo.billify.roles.enums.RoleTypes.*;

/**
 * @author Bryan Lazo (<a href="https://blazo-dev.vercel.app">...</a>)
 * @version 1.0.0
 * @since 4/30/2025
 */
@Component
@Order(1)
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        insertRoleIfNotExists(ROLE_USER.name(), "READ:USER,READ:CUSTOMER");
        insertRoleIfNotExists(ROLE_MANAGER.name(), "READ:USER,READ:CUSTOMER,UPDATE:USER,UPDATE:CUSTOMER");
        insertRoleIfNotExists(ROLE_ADMIN.name(), "READ:USER,READ:CUSTOMER,CREATE:USER,CREATE:CUSTOMER,UPDATE:USER,UPDATE:CUSTOMER");
        insertRoleIfNotExists(ROLE_SYSADMIN.name(), "READ:USER,READ:CUSTOMER,CREATE:USER,CREATE:CUSTOMER,UPDATE:USER,UPDATE:CUSTOMER,DELETE:USER,DELETE:CUSTOMER");
    }

    private void insertRoleIfNotExists(String roleName, String permission) {
        if (roleRepository.findByName(roleName).isEmpty()) {
            Role role = Role.builder().name(roleName).permission(permission).build();
            roleRepository.save(role);
            System.out.println("Inserted role: " + roleName + " with permissions: " + permission);
        }
    }
}

