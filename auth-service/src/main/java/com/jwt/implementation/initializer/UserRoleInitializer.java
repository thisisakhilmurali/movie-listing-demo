package com.jwt.implementation.initializer;

import com.jwt.implementation.model.Role;
import com.jwt.implementation.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserRoleInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository userRoleRepository;

    @Override
    public void run(String... args) {
        initializeRoles();
    }

    private void initializeRoles() {
        if (userRoleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setRole("ROLE_ADMIN");
            userRoleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setRole("ROLE_USER");
            userRoleRepository.save(userRole);
        }
    }
}
