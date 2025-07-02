package com.jmill29.tvtrackerapi.dao;

import java.sql.SQLException;
import java.util.List;

import com.jmill29.tvtrackerapi.dto.UserWatchHistoryDto;
import com.jmill29.tvtrackerapi.model.UserWatchHistory;

public interface UserWatchHistoryDao {

    /**
     * Adds a show to the user's watch history.
     *
     * @param userId the ID of the user
     * @param showId the ID of the show
     * @param status the watch status of the show
     * @return true if the operation was successful, false otherwise
     */
    boolean addShowToWatchHistory(UserWatchHistory userWatchHistory) throws SQLException;

    /**
     * Retrieves the watch history for a specific user.
     *
     * @param userId the ID of the user
     * @return a list of shows in the user's watch history
     */
    List<UserWatchHistoryDto> getWatchHistoryByUserId(int userId, boolean getAll) throws SQLException;

    /**
     * Updates the watch status of a show in the user's watch history.
     *
     * @param userId the ID of the user
     * @param showId the ID of the show
     * @param status the new watch status
     * @return true if the operation was successful, false otherwise
     */
    boolean updateWatchStatus(UserWatchHistory userWatchHistory) throws SQLException;

    /**
     * Deletes a show from the user's watch history.
     *
     * @param userId the ID of the user
     * @param showId the ID of the show
     * @return true if the operation was successful, false otherwise
     */
    boolean deleteShowFromWatchHistory(int userId, int showId) throws SQLException;

    /**
     * Check if a show is already in the user's watch history.
     * @param userId the ID of the user
     * @param showId the ID of the show
     * @return true if the show is in the watch history, false otherwise
     */
    boolean isShowInWatchHistory(int userId, int showId) throws SQLException;
    
}
