package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Roles;
import com.example.demo.model.Users;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UserRepository;

/**
 * Service class that handles user-related business logic such as
 * user retrieval, saving, and role management.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RolesRepository rolesRepository;

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return an Optional containing the user if found, or empty if not found
     */
    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Saves the provided user entity to the database.
     *
     * @param user the user to save
     */
    public void save(Users user) {
        userRepository.save(user);
    }

    /**
     * Finds a role by name, or creates it if it doesn't exist.
     *
     * @param roleName the name of the role
     * @return the existing or newly created {@link Roles} entity
     */
    public Roles findOrCreateRole(String roleName) {
        return rolesRepository.findByName(roleName)
            .orElseGet(() -> {
                Roles newRole = new Roles();
                newRole.setName(roleName);
                return rolesRepository.save(newRole);
            });
    }
}
