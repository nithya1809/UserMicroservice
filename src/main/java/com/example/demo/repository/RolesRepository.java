package com.example.demo.repository;

import com.example.demo.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on {@link Roles} entities.
 * <p>
 * Extends JpaRepository to inherit basic database access methods and includes a custom method
 * for finding a role by its name.
 */
public interface RolesRepository extends JpaRepository<Roles, Long> {

    /**
     * Finds a role by its name.
     *
     * @param name the name of the role
     * @return an Optional containing the role if found, otherwise empty
     */
    Optional<Roles> findByName(String name);
}
