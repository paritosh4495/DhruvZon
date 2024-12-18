package com.Ecommerce.dhruvzon.initializer;

import com.Ecommerce.dhruvzon.enums.Role;
import com.Ecommerce.dhruvzon.model.User;
import com.Ecommerce.dhruvzon.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        createDefaultAdminIfNotExists();
    }

    private void createDefaultAdminIfNotExists() {
        Role adminRole = Role.ADMIN;
        // Create an admin user if it doesn't exist
        if (!userRepository.existsByEmail("admin@example.com")) {
            User adminUser = new User();
            adminUser.setFirstName("ADMIN");
            adminUser.setLastName("Admin_User");
            adminUser.setEmail("admin@example.com");
            adminUser.setPhoneNumber("9403191396");
            adminUser.setAddress("APEKSHA NAGAR");
            adminUser.setPassword(passwordEncoder.encode("password")); // Set a default password
            adminUser.setRole(adminRole); // Assign the admin role
            userRepository.save(adminUser);
            System.out.println("Default Admin created ");
        }
    }


}
