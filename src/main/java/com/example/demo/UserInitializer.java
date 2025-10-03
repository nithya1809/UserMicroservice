package com.example.demo;

import com.example.demo.model.Roles;
import com.example.demo.model.Users;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class UserInitializer {

    @Bean
    public CommandLineRunner loadData(
            RolesRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.count() == 0) {
                Roles userRole = new Roles("ROLE_USER");
                Roles adminRole = new Roles("ROLE_ADMIN");
                roleRepository.save(userRole);
                roleRepository.save(adminRole);

                Set<Roles> userRoles = new HashSet<>();
                userRoles.add(userRole);

                Set<Roles> adminRoles = new HashSet<>();
                adminRoles.add(adminRole);

                if (userRepository.findByUsername("user").isEmpty()) {
                    Users user = new Users(
                            "user",
                            passwordEncoder.encode("password"),
                            userRoles
                    );
                    userRepository.save(user);
                }

                if (userRepository.findByUsername("admin").isEmpty()) {
                    Users admin = new Users(
                            "admin",
                            passwordEncoder.encode("adminpass"),
                            adminRoles
                    );
                    userRepository.save(admin);
                }
            }
        };
    }
}
