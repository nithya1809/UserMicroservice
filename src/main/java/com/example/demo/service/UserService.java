package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Roles;
import com.example.demo.model.Users;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RolesRepository rolesRepository;


    public Optional<Users> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public void save(Users user) {
        userRepository.save(user);
    }

    public Roles findOrCreateRole(String roleName) {
        return rolesRepository.findByName(roleName)
            .orElseGet(() -> {
                Roles newRole = new Roles();
                newRole.setName(roleName);
                return rolesRepository.save(newRole);
            });
    }
}

