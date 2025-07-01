package com.jmill29.tvtrackerapi.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.jmill29.tvtrackerapi.dto.UserDto;
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
     * @return an {@code Optional} containing the {@code UserDto} if found, or empty if not found
     * @throws SQLException if a database access error occurs
     */
    Optional<UserDto> findById(int id) throws SQLException;

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return an {@code Optional} containing the {@code UserDto} if found, or empty if not found
     * @throws SQLException if a database access error occurs
     */
    Optional<UserDto> findByUsername(String username) throws SQLException;

    /**
     * Retrieves all users from the database.
     *
     * @return a {@code List} of all {@code UserDto} objects
     * @throws SQLException if a database access error occurs
     */
    List<UserDto> findAll() throws SQLException;

    /**
     * Saves a new user or updates an existing one.
     * <p>
     * If the user has an ID of 0, a new user will be inserted.
     * Otherwise, the user will be updated based on the provided ID.
     * </p>
     *
     * @param user the {@code User} object to save or update
     * @return {@code true} if the operation was successful, {@code false} otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean save(User user) throws SQLException;

    /**
     * Deletes a user by their unique ID.
     *
     * @param id the ID of the user to delete
     * @return {@code true} if the user was deleted successfully, {@code false} otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean deleteById(int id) throws SQLException;
}
