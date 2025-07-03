package com.jmill29.tvtrackerapi.service;

import java.util.List;

import com.jmill29.tvtrackerapi.dto.UserWatchHistoryDto;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryRequest;
import com.jmill29.tvtrackerapi.exception.DatabaseException;
import com.jmill29.tvtrackerapi.exception.WatchHistoryNotFoundException;

public interface UserWatchHistoryService {

    /**
     * Adds a show to the user's watch history.
     *
     * @param userWatchHistoryRequest the request object containing user and show details
     * @param username the username of the user
     * @return true if the operation was successful, false otherwise
     * @throws IllegalArgumentException if input is invalid
     * @throws DatabaseException if a database access error occurs
     */
    boolean addShowToWatchHistory(UserWatchHistoryRequest userWatchHistoryRequest, String username) throws IllegalArgumentException, DatabaseException;

    /**
     * Retrieves the watch history for a specific user by user ID.
     *
     * @param userId the ID of the user
     * @param getAll whether to retrieve all shows or only those with a watch history entry
     * @return a list of UserWatchHistoryDto objects representing the user's watch history
     * @throws IllegalArgumentException if input is invalid
     * @throws DatabaseException if a database access error occurs
     * @throws WatchHistoryNotFoundException if no watch history is found for the user
     */
    List<UserWatchHistoryDto> getWatchHistoryByUserId(int userId, boolean getAll) throws IllegalArgumentException, DatabaseException, WatchHistoryNotFoundException;

    /**
     * Updates the watch status of a show in the user's watch history.
     *
     * @param userWatchHistoryRequest the request object containing updated watch status
     * @param username the username of the user
     * @return true if the operation was successful, false otherwise
     * @throws IllegalArgumentException if input is invalid
     * @throws DatabaseException if a database access error occurs
     * @throws WatchHistoryNotFoundException if the watch history entry does not exist
     */
    boolean updateWatchStatus(UserWatchHistoryRequest userWatchHistoryRequest, String username) throws IllegalArgumentException, DatabaseException, WatchHistoryNotFoundException;

    /**
     * Deletes a show from the user's watch history.
     *
     * @param username the username of the user
     * @param showId the ID of the show to be deleted
     * @return true if the operation was successful, false otherwise
     * @throws IllegalArgumentException if input is invalid
     * @throws DatabaseException if a database access error occurs
     * @throws WatchHistoryNotFoundException if the watch history entry does not exist
     */
    boolean deleteShowFromWatchHistory(String username, int showId) throws IllegalArgumentException, DatabaseException, WatchHistoryNotFoundException;

    /**
     * Checks if a show is in the user's watch history.
     *
     * @param username the username of the user
     * @param showId the ID of the show
     * @return true if the show is in the user's watch history, false otherwise
     * @throws IllegalArgumentException if input is invalid
     * @throws DatabaseException if a database access error occurs
     */
    boolean isShowInWatchHistory(String username, int showId) throws IllegalArgumentException, DatabaseException;

    /**
     * Retrieves the watch history for a specific user by username.
     *
     * @param username the username of the user
     * @param getAll whether to retrieve all shows or only those with a watch history entry
     * @return a list of UserWatchHistoryDto objects representing the user's watch history
     * @throws IllegalArgumentException if input is invalid
     * @throws DatabaseException if a database access error occurs
     * @throws WatchHistoryNotFoundException if no watch history is found for the user
     */
    List<UserWatchHistoryDto> getWatchHistoryByUsername(String username, boolean getAll) throws IllegalArgumentException, DatabaseException, WatchHistoryNotFoundException;

}
