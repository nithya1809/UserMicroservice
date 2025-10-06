package com.example.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a Role in the system (e.g., ROLE_USER, ROLE_ADMIN).
 * Each role can be associated with one or more users.
 */
@Entity
@Table(name = "roles")
public class Roles {

    /** Unique identifier for the role (auto-generated). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Name of the role (e.g., "ROLE_USER", "ROLE_ADMIN"). */
    private String name;

    /** Default constructor. */
    public Roles() {}

    /**
     * Parameterized constructor.
     *
     * @param name the name of the role
     */
    public Roles(String name) {
        this.name = name;
    }

    /**
     * Gets the role ID.
     *
     * @return the ID of the role
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the role ID.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the role name.
     *
     * @return the name of the role
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the role name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
