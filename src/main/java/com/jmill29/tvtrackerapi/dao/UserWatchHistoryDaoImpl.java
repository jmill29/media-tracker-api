package com.jmill29.tvtrackerapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.jmill29.tvtrackerapi.dto.UserWatchHistoryDto;
import com.jmill29.tvtrackerapi.model.UserWatchHistory;

@Repository
public class UserWatchHistoryDaoImpl implements UserWatchHistoryDao {

    private final DataSource dataSource;

    public UserWatchHistoryDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean addShowToWatchHistory(UserWatchHistory userWatchHistory) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "INSERT INTO user_watch_history (user_id, show_id, status) VALUES (?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setInt(1, userWatchHistory.getUserId());
            pStmt.setInt(2, userWatchHistory.getShowId());
            pStmt.setString(3, userWatchHistory.getStatus().getDbValue()); // Assuming status is an enum
            int rowsAffected = pStmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public List<UserWatchHistoryDto> getWatchHistoryByUserId(int userId, boolean getAll) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query;
            if (!getAll) {
                query = "SELECT uwh.show_id, s.name AS show_name, s.description, s.image_url, uwh.status " +
                        "FROM user_watch_history uwh " +
                        "JOIN tv_shows s ON uwh.show_id = s.show_id AND uwh.user_id = ?";
            } else {
                query = "SELECT uwh.show_id, s.name AS show_name, s.description, s.image_url, uwh.status " +
                        "FROM tv_shows s " +
                        "LEFT JOIN user_watch_history uwh ON s.show_id = uwh.show_id AND uwh.user_id = ? ";
            }
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setInt(1, userId);
            ResultSet rs = pStmt.executeQuery();
            List<UserWatchHistoryDto> watchHistory = new ArrayList<>();
            while (rs.next()) {
                watchHistory.add(mapUserWatchHistoryDto(rs));
            }
            return watchHistory;
        }
    }

    @Override
    public boolean updateWatchStatus(UserWatchHistory userWatchHistory) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "UPDATE user_watch_history SET status = ? WHERE user_id = ? AND show_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setString(1, userWatchHistory.getStatus().getDbValue()); // Assuming status is an enum
            pStmt.setInt(2, userWatchHistory.getUserId());
            pStmt.setInt(3, userWatchHistory.getShowId());
            int rowsAffected = pStmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean deleteShowFromWatchHistory(int userId, int showId) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "DELETE FROM user_watch_history WHERE user_id = ? AND show_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setInt(1, userId);
            pStmt.setInt(2, showId);
            int rowsAffected = pStmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public boolean isShowInWatchHistory(int userId, int showId) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT * FROM user_watch_history WHERE user_id = ? AND show_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setInt(1, userId);
            pStmt.setInt(2, showId);
            ResultSet rs = pStmt.executeQuery();
            
            return rs.next(); // If a row exists, the show is in the watch history
        }
    }
    
    private UserWatchHistoryDto mapUserWatchHistoryDto(ResultSet rs) throws SQLException {
        UserWatchHistoryDto dto = new UserWatchHistoryDto(
            rs.getInt("show_id"),
            rs.getString("show_name"),
            rs.getString("description"),
            rs.getString("image_url"),
            rs.getString("status")!= null ? rs.getString("status"): "Not Watched" // Assuming WatchStatus has a method to convert from DB value
        );
        return dto;
    }

}
