package com.example.demo.repository;

import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository interface for accessing Users entities from the database.
 * <p>
 * Extends JpaRepository to provide basic CRUD operations and query method for finding by username.
 */
public interface UserRepository extends JpaRepository<Users, Long> {

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return an Optional containing the user if found, otherwise empty
     */
    Optional<Users> findByUsername(String username);

}
