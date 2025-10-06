package com.example.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a user in the system.
 * A user has a unique username, an encoded password, and a set of roles.
 */
@Entity
@Table(name = "users")
public class Users {

    /** Unique identifier for the user (auto-generated). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Username of the user. Must be unique. */
    @Column(unique = true)
    private String username;

    /** Encoded password of the user. */
    private String password;

    /**
     * Roles assigned to the user.
     * Many-to-Many relationship with the Roles entity.
     * Eager fetching is used to load roles immediately with the user.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles = new HashSet<>();

    /** Default constructor. */
    public Users() {}

    /**
     * Parameterized constructor.
     *
     * @param username the username
     * @param password the encoded password
     * @param roles    the set of roles
     */
    public Users(String username, String password, Set<Roles> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    /**
     * Gets the user ID.
     *
     * @return the ID
     */
    public Long getId() { return id; }

    /**
     * Sets the user ID.
     *
     * @param id the ID to set
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * Sets the username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Gets the encoded password.
     *
     * @return the password
     */
    public String getPassword() { return password; }

    /**
     * Sets the encoded password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Gets the set of roles.
     *
     * @return the roles
     */
    public Set<Roles> getRoles() { return roles; }

    /**
     * Sets the set of roles.
     *
     * @param roles the roles to assign
     */
    public void setRoles(Set<Roles> roles) { this.roles = roles; }
}
