package com.jmill29.tvtrackerapi.service;

import java.util.List;

import com.jmill29.tvtrackerapi.dto.UserWatchHistoryRequest;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryResponse;
import com.jmill29.tvtrackerapi.exception.DatabaseException;
import com.jmill29.tvtrackerapi.exception.WatchHistoryAlreadyExistsException;
import com.jmill29.tvtrackerapi.exception.WatchHistoryNotFoundException;

/**
 * Service interface for managing user watch history.
 * <p>
 * Defines operations for adding, retrieving, updating, and deleting watch history
 * entries associated with a user and their viewed shows.
 * </p>
 */
public interface UserWatchHistoryService {

    /**
     * Adds a show to the user's watch history.
     *
     * @param userWatchHistoryRequest the request object containing user and show details
     * @param username the username of the user
     * @return {@code true} if the operation was successful, {@code false} otherwise
     * @throws IllegalArgumentException if input is invalid
     * @throws DatabaseException if a database access error occurs
     * @throws WatchHistoryAlreadyExistsException if the watch history entry already exists
     */
    boolean addShowToWatchHistory(UserWatchHistoryRequest userWatchHistoryRequest, String username)
            throws IllegalArgumentException, DatabaseException, WatchHistoryAlreadyExistsException;

    /**
     * Retrieves the watch history for a specific user by user ID.
     *
     * @param userId the ID of the user
     * @param getAll whether to retrieve all shows or only those with a watch history entry
     * @return a list of {@link UserWatchHistoryResponse} objects representing the user's watch history
     * @throws IllegalArgumentException if input is invalid
     * @throws DatabaseException if a database access error occurs
     * @throws WatchHistoryNotFoundException if no watch history is found for the user
     */
    List<UserWatchHistoryResponse> getWatchHistoryByUserId(int userId, boolean getAll)
            throws IllegalArgumentException, DatabaseException, WatchHistoryNotFoundException;

    /**
     * Updates the watch status of a show in the user's watch history.
     *
     * @param userWatchHistoryRequest the request object containing the updated watch status
     * @param username the username of the user
     * @return {@code true} if the operation was successful, {@code false} otherwise
     * @throws IllegalArgumentException if input is invalid
     * @throws DatabaseException if a database access error occurs
     * @throws WatchHistoryNotFoundException if the watch history entry does not exist
     */
    boolean updateWatchStatus(UserWatchHistoryRequest userWatchHistoryRequest, String username)
            throws IllegalArgumentException, DatabaseException, WatchHistoryNotFoundException;

    /**
     * Deletes a show from the user's watch history.
     *
     * @param username the username of the user
     * @param showId the ID of the show to delete
     * @return {@code true} if the operation was successful, {@code false} otherwise
     * @throws IllegalArgumentException if input is invalid
     * @throws DatabaseException if a database access error occurs
     * @throws WatchHistoryNotFoundException if the watch history entry does not exist
     */
    boolean deleteShowFromWatchHistory(String username, int showId)
            throws IllegalArgumentException, DatabaseException, WatchHistoryNotFoundException;

    /**
     * Checks whether a show is already in the user's watch history.
     *
     * @param username the username of the user
     * @param showId the ID of the show
     * @return {@code true} if the show exists in the watch history, {@code false} otherwise
     * @throws IllegalArgumentException if input is invalid
     * @throws DatabaseException if a database access error occurs
     */
    boolean isShowInWatchHistory(String username, int showId)
            throws IllegalArgumentException, DatabaseException;

    /**
     * Retrieves the watch history for a specific user by username.
     *
     * @param username the username of the user
     * @param getAll whether to retrieve all shows or only those with a watch history entry
     * @return a list of {@link UserWatchHistoryResponse} objects representing the user's watch history
     * @throws IllegalArgumentException if input is invalid
     * @throws DatabaseException if a database access error occurs
     * @throws WatchHistoryNotFoundException if no watch history is found for the user
     */
    List<UserWatchHistoryResponse> getWatchHistoryByUsername(String username, boolean getAll)
            throws IllegalArgumentException, DatabaseException, WatchHistoryNotFoundException;
}
