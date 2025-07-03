package com.jmill29.tvtrackerapi.dao;

import java.sql.SQLException;
import java.util.List;

import com.jmill29.tvtrackerapi.dto.UserWatchHistoryDto;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryRequest;

public interface UserWatchHistoryDao {

    /**
     * Adds a show to the user's watch history.
     *
     * @param userWatchHistoryRequest the UserWatchHistoryRequest object containing status and show id
     * @param username the username of the user
     * @return true if the operation was successful, false otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean addShowToWatchHistory(UserWatchHistoryRequest userWatchHistoryRequest, String username) throws SQLException;

    /**
     * Retrieves the watch history for a specific user by user ID.
     *
     * @param userId the ID of the user
     * @param getAll whether to retrieve all shows or only those with a watch history entry
     * @return a list of UserWatchHistoryDto objects representing the user's watch history
     * @throws SQLException if a database access error occurs
     */
    List<UserWatchHistoryDto> getWatchHistoryByUserId(int userId, boolean getAll) throws SQLException;

    /**
     * Updates the watch status of a show in the user's watch history.
     *
     * @param userWatchHistoryRequest the UserWatchHistoryRequest object containing updated watch status and show id
     * @param username the username of the user
     * @return true if the operation was successful, false otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean updateWatchStatus(UserWatchHistoryRequest userWatchHistoryRequest, String username) throws SQLException;

    /**
     * Deletes a show from the user's watch history.
     *
     * @param username the username of the user
     * @param showId the ID of the show
     * @return true if the operation was successful, false otherwise
     */
    boolean deleteShowFromWatchHistory(String username, int showId) throws SQLException;

    /**
     * Checks if a show is in the user's watch history.
     *
     * @param username the username of the user
     * @param showId the ID of the show
     * @return true if the show is in the watch history, false otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean isShowInWatchHistory(String username, int showId) throws SQLException;

    /**
     * Retrieves the watch history for a specific user by username.
     *
     * @param username the username of the user
     * @param getAll whether to retrieve all shows or only those with a watch history entry
     * @return a list of UserWatchHistoryDto objects representing the user's watch history
     * @throws SQLException if a database access error occurs
     */
    List<UserWatchHistoryDto> getWatchHistoryByUsername(String username, boolean getAll) throws SQLException;
}
