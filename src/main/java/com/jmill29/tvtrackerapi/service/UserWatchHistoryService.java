package com.jmill29.tvtrackerapi.service;

import java.sql.SQLException;
import java.util.List;

import com.jmill29.tvtrackerapi.dto.UserWatchHistoryDto;
import com.jmill29.tvtrackerapi.model.UserWatchHistory;

public interface UserWatchHistoryService {

    /**
     * Adds a show to the user's watch history.
     *
     * @param userWatchHistory the UserWatchHistory object containing user and show details
     * @return true if the operation was successful, false otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean addShowToWatchHistory(UserWatchHistory userWatchHistory) throws SQLException;

    /**
     * Retrieves the watch history for a specific user.
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
     * @param userWatchHistory the UserWatchHistory object containing updated watch status
     * @return true if the operation was successful, false otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean updateWatchStatus(UserWatchHistory userWatchHistory) throws SQLException;

    /**
     * Deletes a show from the user's watch history.
     *
     * @param userId the ID of the user
     * @param showId the ID of the show to be deleted
     * @return true if the operation was successful, false otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean deleteShowFromWatchHistory(int userId, int showId) throws SQLException;

    /**
     * Retrieves all shows in the user's watch history, regardless of whether they have a watch history entry.
     *
     * @param userId the ID of the user
     * @return a list of UserWatchHistoryDto objects representing all shows in the user's watch history
     * @throws SQLException if a database access error occurs
     */
    boolean isShowInWatchHistory(int userId, int showId) throws SQLException;

}
