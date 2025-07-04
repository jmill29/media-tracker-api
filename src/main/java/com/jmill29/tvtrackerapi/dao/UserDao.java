package com.jmill29.tvtrackerapi.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.jmill29.tvtrackerapi.dto.UserResponse;
import com.jmill29.tvtrackerapi.exception.UserAlreadyExistsException;
import com.jmill29.tvtrackerapi.exception.UserNotFoundException;
import com.jmill29.tvtrackerapi.model.User;

/**
 * Data Access Object interface for managing {@link User} records in the database.
 * <p>
 * Provides an abstraction layer for CRUD operations and user lookups. Most read operations return
 * {@link UserResponse} objects to avoid exposing sensitive fields like passwords.
 * </p>
 */
public interface UserDao {

    /**
     * Finds a user by their unique ID.
     * <p>
     * Searches the database for a user with the specified ID and returns the corresponding {@link UserResponse}
     * wrapped in an {@link Optional}, or an empty {@code Optional} if no user is found.
     * </p>
     *
     * @param id the ID of the user to find
     * @return an {@code Optional<UserResponse>} if the user exists, or {@code Optional.empty()} otherwise
     * @throws SQLException if a database access error occurs
     */
    Optional<UserResponse> findById(int id) throws SQLException;

    /**
     * Finds a user by their username.
     * <p>
     * Searches the database for a user with the given username. This is typically used during authentication.
     * </p>
     *
     * @param username the username to search for
     * @return an {@code Optional<UserResponse>} if the user exists, or {@code Optional.empty()} otherwise
     * @throws SQLException if a database access error occurs
     */
    Optional<UserResponse> findByUsername(String username) throws SQLException;

    /**
     * Retrieves all users from the database.
     * <p>
     * Returns a list of all users as {@link UserResponse} objects, excluding sensitive fields such as passwords.
     * </p>
     *
     * @return a {@code List<UserResponse>} of all users in the system
     * @throws SQLException if a database access error occurs
     */
    List<UserResponse> findAll() throws SQLException;

    /**
     * Saves a user to the database.
     * <p>
     * If the {@code userId} of the given {@link User} object is 0, a new user is inserted.
     * Otherwise, the existing user is updated based on their ID.
     * </p>
     *
     * @param user the {@link User} object to insert or update
     * @return {@code true} if the operation was successful, {@code false} otherwise
     * @throws SQLException if a database access error occurs
     * @throws UserAlreadyExistsException if the user already exists when attempting to create a new user
     * @throws UserNotFoundException if the user does not exist when attempting to update
     */
    boolean save(User user) throws SQLException, UserAlreadyExistsException, UserNotFoundException;

    /**
     * Deletes a user by their unique ID.
     * <p>
     * Removes the user record with the given ID from the database.
     * </p>
     *
     * @param id the ID of the user to delete
     * @return {@code true} if the user was deleted successfully, {@code false} otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean deleteById(int id) throws SQLException;

}

