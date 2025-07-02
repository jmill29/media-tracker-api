package com.jmill29.tvtrackerapi.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jmill29.tvtrackerapi.dao.UserWatchHistoryDao;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryDto;
import com.jmill29.tvtrackerapi.exception.WatchHistoryAlreadyExistsException;
import com.jmill29.tvtrackerapi.exception.WatchHistoryNotFoundException;
import com.jmill29.tvtrackerapi.model.UserWatchHistory;

@Service
public class UserWatchHistoryServiceImpl implements UserWatchHistoryService {

    private final UserWatchHistoryDao userWatchHistoryDao;

    public UserWatchHistoryServiceImpl(UserWatchHistoryDao userWatchHistoryDao) {
        this.userWatchHistoryDao = userWatchHistoryDao;
    }

    @Override
    public boolean addShowToWatchHistory(UserWatchHistory userWatchHistory) throws SQLException, WatchHistoryAlreadyExistsException {
        // Check if the show is already in the user's watch history
        if (userWatchHistoryDao.isShowInWatchHistory(userWatchHistory.getUserId(), userWatchHistory.getShowId())) {
            // If it is, throw a WatchHistoryAlreadyExistsException
            throw new WatchHistoryAlreadyExistsException(
                "Show with ID " + userWatchHistory.getShowId() + " is already in the watch history for user ID " + userWatchHistory.getUserId()
            );
        }

        return userWatchHistoryDao.addShowToWatchHistory(userWatchHistory);
    }

    @Override
    public List<UserWatchHistoryDto> getWatchHistoryByUserId(int userId, boolean getAll) throws SQLException, WatchHistoryNotFoundException {
        List<UserWatchHistoryDto> watchHistories = userWatchHistoryDao.getWatchHistoryByUserId(userId, getAll);

        // check if the list is empty
        if (!getAll && watchHistories.isEmpty()) {
            // If the list is empty, throw a WatchHistoryNotFoundException
            throw new WatchHistoryNotFoundException(
                "No watch history found for user ID " + userId
            );
        }

        return watchHistories;
    }

    @Override
    public boolean updateWatchStatus(UserWatchHistory userWatchHistory) throws SQLException, WatchHistoryNotFoundException {
        if (userWatchHistoryDao.updateWatchStatus(userWatchHistory)) {
            return true;
        } else {
            // If the update fails, throw a UserWatchHistoryNotFoundException
            throw new WatchHistoryNotFoundException(
                "Failed to update watch status for show ID " + userWatchHistory.getShowId() + " for user ID " + userWatchHistory.getUserId()
            );
        }
    }

    @Override
    public boolean deleteShowFromWatchHistory(int userId, int showId) throws SQLException, WatchHistoryNotFoundException {
        if (userWatchHistoryDao.deleteShowFromWatchHistory(userId, showId)) {
            return true;
        } else {
            // If the deletion fails, throw a WatchHistoryNotFoundException
            throw new WatchHistoryNotFoundException(
                "Failed to delete show ID " + showId + " from watch history for user ID " + userId
            );
        }
    }

    @Override
    public boolean isShowInWatchHistory(int userId, int showId) throws SQLException {
        return userWatchHistoryDao.isShowInWatchHistory(userId, showId);
    }



} 
