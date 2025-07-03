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
 * Delegates user CRUD and lookup operations to the UserDao.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    /**
     * Constructs a UserServiceImpl with the given UserDao.
     * @param userDao the UserDao to use for data access
     */
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<UserResponse> findById(int id) throws IllegalArgumentException, DatabaseException {
        // check if id greater than 0
        if (id <= 0) {
            throw new IllegalArgumentException("User ID must be greater than 0");
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
            throw new IllegalArgumentException("Username cannot be null or empty");
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
            throw new DatabaseException("Error retrieiving list of users " + ", " + e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean save(User user) throws IllegalArgumentException, DatabaseException, UserNotFoundException, UserAlreadyExistsException {
        // check if user = null
        if (user == null) {
            throw new IllegalArgumentException("Must include a Request Body");
        } 

        try {
            return userDao.save(user);
        } catch (SQLException ex) {
            throw new DatabaseException("Error saving new user info for user with ID " + user.getUserId() + ", " + ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteById(int id) throws IllegalArgumentException, DatabaseException {
        // check if id <= 0
        if (id <= 0) {
            throw new IllegalArgumentException("User ID must be greater than 0");
        }

        try {
            return userDao.deleteById(id);
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting user with ID, " + id + ": " + e);
        }
    }

    
}
