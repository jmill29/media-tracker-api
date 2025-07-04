package com.jmill29.tvtrackerapi.dao;

import java.sql.SQLException;
import java.util.List;

import com.jmill29.tvtrackerapi.dto.UserWatchHistoryRequest;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryResponse;

/**
 * Data Access Object interface for managing user watch history in the database.
 * <p>
 * Provides methods for adding, retrieving, updating, and deleting watch history records for users.
 * </p>
 */
public interface UserWatchHistoryDao {

    /**
     * Adds a show to the user's watch history.
     *
     * @param userWatchHistoryRequest the {@link UserWatchHistoryRequest} object containing status and show id
     * @param username the username of the user
     * @return {@code true} if the operation was successful, {@code false} otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean addShowToWatchHistory(UserWatchHistoryRequest userWatchHistoryRequest, String username) throws SQLException;

    /**
     * Retrieves the watch history for a specific user by user ID.
     *
     * @param userId the ID of the user
     * @param getAll if {@code true}, retrieves all shows; if {@code false}, only shows with a watch history entry
     * @return a list of {@link UserWatchHistoryResponse} objects representing the user's watch history
     * @throws SQLException if a database access error occurs
     */
    List<UserWatchHistoryResponse> getWatchHistoryByUserId(int userId, boolean getAll) throws SQLException;

    /**
     * Updates the watch status of a show in the user's watch history.
     *
     * @param userWatchHistoryRequest the {@link UserWatchHistoryRequest} object containing updated watch status and show id
     * @param username the username of the user
     * @return {@code true} if the operation was successful, {@code false} otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean updateWatchStatus(UserWatchHistoryRequest userWatchHistoryRequest, String username) throws SQLException;

    /**
     * Deletes a show from the user's watch history.
     *
     * @param username the username of the user
     * @param showId the ID of the show (matches the {@code show_id} field)
     * @return {@code true} if the operation was successful, {@code false} otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean deleteShowFromWatchHistory(String username, int showId) throws SQLException;

    /**
     * Checks if a show is in the user's watch history.
     *
     * @param username the username of the user
     * @param showId the ID of the show (matches the {@code show_id} field)
     * @return {@code true} if the show is in the watch history, {@code false} otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean isShowInWatchHistory(String username, int showId) throws SQLException;

    /**
     * Retrieves the watch history for a specific user by username.
     *
     * @param username the username of the user
     * @param getAll see {@link #getWatchHistoryByUserId(int, boolean)} for meaning of {@code getAll}
     * @return a list of {@link UserWatchHistoryResponse} objects representing the user's watch history
     * @throws SQLException if a database access error occurs
     */
    List<UserWatchHistoryResponse> getWatchHistoryByUsername(String username, boolean getAll) throws SQLException;
}
