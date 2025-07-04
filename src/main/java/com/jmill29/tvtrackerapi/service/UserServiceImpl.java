package com.jmill29.tvtrackerapi.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jmill29.tvtrackerapi.dao.UserDao;
import com.jmill29.tvtrackerapi.dto.UserResponse;
import com.jmill29.tvtrackerapi.exception.DatabaseException;
import com.jmill29.tvtrackerapi.exception.UserAlreadyExistsException;
import com.jmill29.tvtrackerapi.exception.UserNotFoundException;
import com.jmill29.tvtrackerapi.model.User;

/**
 * Service implementation for user-related operations.
 * <p>
 * This class provides the business logic for handling user data,
 * delegating persistence operations to the {@link UserDao}.
 * </p>
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    /**
     * Constructs a {@code UserServiceImpl} with the specified {@code UserDao}.
     *
     * @param userDao the data access object used to interact with user data
     */
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserResponse> findById(int id) throws IllegalArgumentException, DatabaseException {
        if (id <= 0) {
            throw new IllegalArgumentException("User ID must be greater than 0.");
        }

        try {
            return userDao.findById(id);
        } catch (SQLException ex) {
            throw new DatabaseException("Error retrieving user by ID: " + id + ", " + ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserResponse> findByUsername(String username) throws IllegalArgumentException, DatabaseException {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }

        try {
            return userDao.findByUsername(username);
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving user by username: " + username + ", " + e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserResponse> findAll() throws DatabaseException {
        try {
            return userDao.findAll();
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving list of users: " + e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(User user) throws IllegalArgumentException, DatabaseException, UserNotFoundException, UserAlreadyExistsException {
        if (user == null) {
            throw new IllegalArgumentException("User request body must not be null.");
        }

        try {
            return userDao.save(user);
        } catch (SQLException ex) {
            throw new DatabaseException("Error saving user with ID " + user.getUserId() + ": " + ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(int id) throws IllegalArgumentException, DatabaseException {
        if (id <= 0) {
            throw new IllegalArgumentException("User ID must be greater than 0.");
        }

        try {
            return userDao.deleteById(id);
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting user with ID " + id + ": " + e);
        }
    }
}
