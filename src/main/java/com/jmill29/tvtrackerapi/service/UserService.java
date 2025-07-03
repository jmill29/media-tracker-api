package com.jmill29.tvtrackerapi.service;

import java.util.List;
import java.util.Optional;

import com.jmill29.tvtrackerapi.dto.UserResponse;
import com.jmill29.tvtrackerapi.exception.DatabaseException;
import com.jmill29.tvtrackerapi.model.User;

/**
 * Service layer interface for handling business logic related to users.
 * <p>
 * This interface defines operations for retrieving, saving, and deleting users,
 * abstracting the underlying data access implementation.
 * </p>
 */
public interface UserService {

    /**
     * Finds a user by their unique ID.
     *
     * @param id the ID of the user
     * @return an {@code Optional} containing the {@code UserResponse} if found, or empty if not found
     * @throws IllegalArgumentException if the ID is invalid (e.g., less than or equal to 0)
     * @throws DatabaseException if a database access error occurs
     */
    Optional<UserResponse> findById(int id) throws IllegalArgumentException, DatabaseException;

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return an {@code Optional} containing the {@code UserResponse} if found, or empty if not found
     * @throws IllegalArgumentException if the username is null or empty
     * @throws DatabaseException if a database access error occurs
     */
    Optional<UserResponse> findByUsername(String username) throws IllegalArgumentException, DatabaseException;

    /**
     * Retrieves all users from the database.
     *
     * @return a {@code List} of all {@code UserResponse} objects
     * @throws DatabaseException if a database access error occurs
     */
    List<UserResponse> findAll() throws DatabaseException;

    /**
     * Saves a new user or updates an existing one.
     * <p>
     * If the user has an ID of 0, a new user will be inserted.
     * Otherwise, the user will be updated based on the provided ID.
     * </p>
     *
     * @param user the {@code User} object to save or update
     * @return {@code true} if the operation was successful, {@code false} otherwise
     * @throws IllegalArgumentException if the user object is null or contains invalid fields
     * @throws DatabaseException if a database access error occurs
     */
    boolean save(User user) throws IllegalArgumentException, DatabaseException;

    /**
     * Deletes a user by their unique ID.
     *
     * @param id the ID of the user to delete
     * @return {@code true} if the user was deleted successfully, {@code false} otherwise
     * @throws IllegalArgumentException if the ID is invalid (e.g., less than or equal to 0)
     * @throws DatabaseException if a database access error occurs
     */
    boolean deleteById(int id) throws IllegalArgumentException, DatabaseException;
}
