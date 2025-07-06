package com.jmill29.tvtrackerapi.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jmill29.tvtrackerapi.dao.UserWatchHistoryDao;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryRequest;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryResponse;
import com.jmill29.tvtrackerapi.exception.DatabaseException;
import com.jmill29.tvtrackerapi.exception.ShowNotFoundException;
import com.jmill29.tvtrackerapi.exception.UserNotFoundException;
import com.jmill29.tvtrackerapi.exception.WatchHistoryAlreadyExistsException;
import com.jmill29.tvtrackerapi.exception.WatchHistoryNotFoundException;


/**
 * Service implementation for user watch history operations.
 * Handles business logic and exception translation for watch history features.
 */
@Service
public class UserWatchHistoryServiceImpl implements UserWatchHistoryService {

    private final UserWatchHistoryDao userWatchHistoryDao;
    private final UserService userService;
    private final ShowService showService;

    public UserWatchHistoryServiceImpl(
        UserWatchHistoryDao userWatchHistoryDao,
        UserService userService,
        ShowService showService) {
        this.userWatchHistoryDao = userWatchHistoryDao;
        this.userService = userService;
        this.showService = showService;
    }

    /** {@inheritDoc} */
    @Override
    public boolean addShowToWatchHistory(UserWatchHistoryRequest userWatchHistoryRequest, String username)
        throws IllegalArgumentException, DatabaseException, WatchHistoryAlreadyExistsException, ShowNotFoundException {
        // check if userWatchHistoryRequest is null
        if (userWatchHistoryRequest == null) {
            throw new IllegalArgumentException("Must include a Request Body");
        }

        validateUsername(username);

        // Check if the show is already in the user's watch history
        if (isShowInWatchHistory(username, userWatchHistoryRequest.getShowId())) {
            throw new WatchHistoryAlreadyExistsException(
                "Show ID " + userWatchHistoryRequest.getShowId() + " is already in watch history for user " + username
            );
        }

        if (showService.findById(userWatchHistoryRequest.getShowId()).isEmpty()) {
            throw new ShowNotFoundException("Show with ID, " + userWatchHistoryRequest.getShowId() + ", not found");
        }

        try {
            return userWatchHistoryDao.addShowToWatchHistory(userWatchHistoryRequest, username);
        } catch (SQLException ex) {
            // If a SQLException occurs, wrap it in a DatabaseException
            throw new DatabaseException("Database error occurred while adding show ID " + userWatchHistoryRequest.getShowId() + " to watch history for user " + username + ", " + ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserWatchHistoryResponse> getWatchHistoryByUserId(int userId, boolean getAll) throws IllegalArgumentException, DatabaseException, WatchHistoryNotFoundException {
        try {
            // check if userId is less than or equal to 0
            if (userId <= 0) {
                throw new IllegalArgumentException("User ID must be greater than 0");
            }

            // check if user exists by userId
            if (userService.findById(userId).isEmpty()) {
                throw new UserNotFoundException(
                    "User with ID " + userId + " does not exist"
                );
            }

            List<UserWatchHistoryResponse> watchHistories = userWatchHistoryDao.getWatchHistoryByUserId(userId, getAll);
            
            // check if the list is empty
            if (!getAll && watchHistories.isEmpty()) {
                throw new WatchHistoryNotFoundException(
                        "No watch history found for user ID " + userId
                );
            }
            
            return watchHistories;
        } catch (SQLException ex) {
            throw new DatabaseException("Database error occurred while retrieving watch history for user ID " + userId + ", " + ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateWatchStatus(UserWatchHistoryRequest userWatchHistoryRequest, String username) throws IllegalArgumentException, DatabaseException, WatchHistoryNotFoundException {
        if (userWatchHistoryRequest == null) {
            throw new IllegalArgumentException("UserWatchHistory cannot be null");
        }

        validateUsername(username);

        if (showService.findById(userWatchHistoryRequest.getShowId()).isEmpty()) {
            throw new ShowNotFoundException("Show with ID, " + userWatchHistoryRequest.getShowId() + ", not found");
        }
        
        // check if watch history exists for the user and show
        if (!this.isShowInWatchHistory(username, userWatchHistoryRequest.getShowId())) {
            throw new WatchHistoryNotFoundException(
                    "Watch history for item with ID " + userWatchHistoryRequest.getShowId() + " for user " + username + " does not exist"
            );
        } 

        try {
            if (userWatchHistoryDao.updateWatchStatus(userWatchHistoryRequest, username)) {
                return true;
            } else {
                throw new WatchHistoryNotFoundException(
                        "Failed to update watch status for show ID " + userWatchHistoryRequest.getShowId() + " for user " + username
                );
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Database error occurred while updating watch status for user " + username + ", " + ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteShowFromWatchHistory(String username, int showId) throws 
        IllegalArgumentException, DatabaseException, WatchHistoryNotFoundException, ShowNotFoundException {
        validateUsername(username);

        if (showId <= 0) {
            throw new IllegalArgumentException("Show ID must be greater than 0");
        }

        if (showService.findById(showId).isEmpty()) {
            throw new ShowNotFoundException("Show with ID, " + showId + ", not found");
        }
        
        try {
            if (userWatchHistoryDao.deleteShowFromWatchHistory(username, showId)) {
                return true;
            } else {
                throw new WatchHistoryNotFoundException(
                        "Failed to delete show ID " + showId + " from watch history for user " + username
                );
            }
        } catch (SQLException ex) {
            throw new DatabaseException("Database error occurred while deleting show ID " + showId + " from watch history for user with username " + username + ", " + ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShowInWatchHistory(String username, int showId) throws IllegalArgumentException, DatabaseException {
        validateUsername(username);

        if (showId <= 0) {
            throw new IllegalArgumentException("ShowId must be greater than 0");
        }

        try {
            return userWatchHistoryDao.isShowInWatchHistory(username, showId);
        } catch (SQLException ex) {
            throw new DatabaseException("Database error occurred while checking if show ID " + showId + " is in watch history for user " + username + ", " + ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserWatchHistoryResponse> getWatchHistoryByUsername(String username, boolean getAll) throws IllegalArgumentException, DatabaseException, WatchHistoryNotFoundException {
        validateUsername(username);

        try {
            List<UserWatchHistoryResponse> watchHistories = userWatchHistoryDao.getWatchHistoryByUsername(username, getAll);

            if (!getAll && watchHistories.isEmpty()) {
                throw new WatchHistoryNotFoundException(
                    "No watch history found for user with username " + username
                );
            }

            return watchHistories;
        } catch (SQLException e) {
            throw new DatabaseException("Database error occurred while retrieving watch history for user " + username + ", " + e);
        }
    }

    /**
     * Validates the provided username and checks if the user exists.
     *
     * @param username the username to validate
     * @throws IllegalArgumentException if the username is null or empty
     * @throws UserNotFoundException if the user does not exist in the system
     */
    private void validateUsername(String username) throws IllegalArgumentException, UserNotFoundException {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        if (userService.findByUsername(username).isEmpty()) {
            throw new UserNotFoundException("User with username " + username + " does not exist");
        }
    }
} 
