package com.jmill29.tvtrackerapi.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jmill29.tvtrackerapi.dao.UserDao;
import com.jmill29.tvtrackerapi.dto.UserDto;
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

    @Override
    /**
     * Finds a user by their unique ID.
     * @param id the user ID
     * @return an Optional containing the UserDto if found, or empty if not found
     */
    public Optional<UserDto> findById(int id) throws SQLException {
        return userDao.findById(id);
    }

    @Override
    /**
     * Finds a user by their username.
     * @param username the username to search for
     * @return an Optional containing the UserDto if found, or empty if not found
     */
    public Optional<UserDto> findByUsername(String username) throws SQLException {
        return userDao.findByUsername(username);
    }

    @Override
    /**
     * Retrieves all users from the database.
     * @return a list of all UserDto objects
     */
    public List<UserDto> findAll() throws SQLException {
        return userDao.findAll();
    }

    @Override
    /**
     * Saves a new user or updates an existing user in the database.
     * @param user the User object to save or update
     * @return true if the operation was successful, false otherwise
     */
    public boolean save(User user) throws SQLException {
        return userDao.save(user);
    }

    @Override
    /**
     * Deletes a user by their unique ID.
     * @param id the user ID to delete
     * @return true if the user was deleted successfully, false otherwise
     */
    public boolean deleteById(int id) throws SQLException {
        return userDao.deleteById(id);
    }

    
}
