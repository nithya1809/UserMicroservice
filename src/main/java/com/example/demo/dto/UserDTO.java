package com.example.demo.dto;

import java.util.List;

/**
 * Data Transfer Object (DTO) for transferring user-related data
 * between client, controller, and service layers.
 * This class includes basic user details such as:
 * - Username
 * - Password
 * - Roles
 */
public class UserDTO {

    /** The username of the user */
    private String username;

    /** The password of the user (usually in plain text before encoding) */
    private String password;

    /** List of role names associated with the user (e.g., ROLE_USER, ROLE_ADMIN) */
    private List<String> roles;

    /** Default constructor */
    public UserDTO() {}

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the list of roles.
     * @return list of role names
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Sets the list of roles.
     * @param roles list of role names to set
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
