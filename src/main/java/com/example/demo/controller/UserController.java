package com.example.demo.controller;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Roles;
import com.example.demo.model.Users;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) {
        Optional<Users> optionalUser = userService.findByUsername(username);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Users user = optionalUser.get();

        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword()); 
        dto.setRoles(user.getRoles().stream()
            .map(role -> role.getName())
            .collect(Collectors.toList()));

        return ResponseEntity.ok(dto);
    }
   

        @PostMapping("/register")
        public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
            if (userService.findByUsername(userDTO.getUsername()).isPresent()) {
                return ResponseEntity.badRequest().body("Username already exists");
            }

            Users newUser = new Users();
            newUser.setUsername(userDTO.getUsername());
            newUser.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encode password

            Set<Roles> roles = userDTO.getRoles().stream()
                .map(roleName -> userService.findOrCreateRole(roleName))
                .collect(Collectors.toSet());
            newUser.setRoles(roles);

            userService.save(newUser);
            return ResponseEntity.ok("User registered successfully");
        }

        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
            Optional<Users> optionalUser = userService.findByUsername(userDTO.getUsername());
            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(401).body("Invalid credentials");
            }

            Users user = optionalUser.get();
            if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
                return ResponseEntity.status(401).body("Invalid credentials");
            }

            return ResponseEntity.ok("Login successful");
        }
    }



