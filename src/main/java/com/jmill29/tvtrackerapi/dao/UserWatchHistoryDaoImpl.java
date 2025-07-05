package com.jmill29.tvtrackerapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.jmill29.tvtrackerapi.dto.UserWatchHistoryRequest;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryResponse;
import com.jmill29.tvtrackerapi.exception.UserNotFoundException;

/**
 * JDBC-based implementation of the {@link UserWatchHistoryDao} interface for managing user watch history in the database.
 * <p>
 * Provides methods for adding, retrieving, updating, and deleting watch history records for users using direct JDBC queries.
 * </p>
 */
@Repository
public class UserWatchHistoryDaoImpl implements UserWatchHistoryDao {

    private final DataSource dataSource;


    /**
     * Constructs a new {@code UserWatchHistoryDaoImpl} with the given data source.
     *
     * @param dataSource the {@link DataSource} for database connections
     */
    public UserWatchHistoryDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /** {@inheritDoc} */
    @Override
    public boolean addShowToWatchHistory(UserWatchHistoryRequest userWatchHistoryRequest, String username) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            int userId = getUserIdByUsername(conn, username);
            String query = "INSERT INTO user_watch_history (user_id, show_id, status) VALUES (?, ?, ?)";
            try (PreparedStatement pStmt = conn.prepareStatement(query)) {
                pStmt.setInt(1, userId);
                pStmt.setInt(2, userWatchHistoryRequest.getShowId());
                // Store status as its DB value (enum to string)
                pStmt.setString(3, userWatchHistoryRequest.getStatus().getDbValue());
                int rowsAffected = pStmt.executeUpdate();
                // Returns true if a row was inserted
                return rowsAffected > 0;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<UserWatchHistoryResponse> getWatchHistoryByUserId(int userId, boolean getAll) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query;
            if (!getAll) {
                // Only shows the user has interacted with (INNER JOIN)
                query = "SELECT uwh.show_id, s.show_name AS show_name, s.description, s.image_url, uwh.status " +
                        "FROM user_watch_history uwh " +
                        "JOIN tv_shows s ON uwh.show_id = s.show_id AND uwh.user_id = ?";
            } else {
                // All shows, with user's status if present (LEFT JOIN)
                query = "SELECT uwh.show_id, s.show_name AS show_name, s.description, s.image_url, uwh.status " +
                        "FROM tv_shows s " +
                        "LEFT JOIN user_watch_history uwh ON s.show_id = uwh.show_id AND uwh.user_id = ? ";
            }
            try (PreparedStatement pStmt = conn.prepareStatement(query)) {
                pStmt.setInt(1, userId);
                try (ResultSet rs = pStmt.executeQuery()) {
                    List<UserWatchHistoryResponse> watchHistory = new ArrayList<>();
                    while (rs.next()) {
                        // Map each row to a DTO, handling null status
                        watchHistory.add(mapUserWatchHistoryDto(rs));
                    }
                    return watchHistory;
                }
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean updateWatchStatus(UserWatchHistoryRequest userWatchHistory, String username) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            int userId = getUserIdByUsername(conn, username);
            String query = "UPDATE user_watch_history SET status = ? WHERE show_id = ? AND user_id = ?";
            try (PreparedStatement pStmt = conn.prepareStatement(query)) {
                // Update the status for a specific user-show pair
                pStmt.setString(1, userWatchHistory.getStatus().getDbValue());
                pStmt.setInt(2, userWatchHistory.getShowId());
                pStmt.setInt(3, userId);
                int rowsAffected = pStmt.executeUpdate();
                // Returns true if a row was updated
                return rowsAffected > 0;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean deleteShowFromWatchHistory(String username, int showId) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            int id = getUserIdByUsername(conn, username);
            String query = "DELETE FROM user_watch_history WHERE show_id = ? AND user_id = ?";
            try (PreparedStatement pStmt = conn.prepareStatement(query)) {
                pStmt.setInt(1, showId);
                pStmt.setInt(2, id);
                int rowsAffected = pStmt.executeUpdate();
                // Returns true if a row was deleted
                return rowsAffected > 0;
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean isShowInWatchHistory(String username, int showId) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            int id = getUserIdByUsername(conn, username);
            String query = "SELECT 1 FROM user_watch_history WHERE user_id = ? " +
                            "AND show_id = ?";
            try (PreparedStatement pStmt = conn.prepareStatement(query)) {
                pStmt.setInt(1, id);
                pStmt.setInt(2, showId);
                try (ResultSet rs = pStmt.executeQuery()) {
                    // Returns true if a row exists for this user-show pair
                    return rs.next();
                }
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<UserWatchHistoryResponse> getWatchHistoryByUsername(String username, boolean getAll) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query;
            if (!getAll) {
                // Only shows the user has interacted with (INNER JOIN)
                query = "SELECT uwh.show_id, s.show_name AS show_name, s.description, s.image_url, uwh.status " +
                        "FROM user_watch_history uwh " +
                        "JOIN tv_shows s ON uwh.show_id = s.show_id " +
                        "JOIN users u ON uwh.user_id = u.user_id AND u.username = ?";
            } else {
                // All shows, with user's status if present (LEFT JOIN)
                query = "SELECT s.show_id, s.show_name AS show_name, s.description, s.image_url, uwh.status " +
                        "FROM tv_shows s " +
                        "LEFT JOIN user_watch_history uwh ON s.show_id = uwh.show_id AND uwh.user_id = ?";
            }
            try (PreparedStatement pStmt = conn.prepareStatement(query)) {
                if (!getAll) {
                    pStmt.setString(1, username);
                } else {
                    int id = getUserIdByUsername(conn, username);
                    pStmt.setInt(1, id);
                }
                try (ResultSet rs = pStmt.executeQuery()) {
                    List<UserWatchHistoryResponse> watchHistory = new ArrayList<>();
                    while (rs.next()) {
                        // Map each row to a DTO, handling null status
                        watchHistory.add(mapUserWatchHistoryDto(rs));
                    }
                    return watchHistory;
                }
            }
        }
    }

    

    /**
     * Maps a {@link ResultSet} row to a {@link UserWatchHistoryResponse} object.
     * If the {@code status} column is {@code null}, defaults to {@code "Not Watched"}.
     *
     * @param rs the {@link ResultSet} positioned at a row
     * @return the mapped {@link UserWatchHistoryResponse} object
     * @throws SQLException if a database access error occurs
     */
    private static UserWatchHistoryResponse mapUserWatchHistoryDto(ResultSet rs) throws SQLException {
        // Map a ResultSet row to a UserWatchHistoryResponse, defaulting status if null
        return new UserWatchHistoryResponse(
            rs.getInt("show_id"),
            rs.getString("show_name"),
            rs.getString("description"),
            rs.getString("image_url"),
            rs.getString("status") != null ? rs.getString("status") : "Not Watched"
        );
    }

    /**
     * Retrieves the user ID for a given username.
     * <p>
     * Asssumes the username has already been validated to exist in the service layer.
     * </p>
     *
     * @param conn the active {@link Connection} to the database
     * @param username the username to look up
     * @return the User ID associated with the username 
     * @throws SQLException if a database access error occurs
     */
    private int getUserIdByUsername(Connection conn, String username) throws SQLException {
        String query = "SELECT user_id FROM users WHERE username = ?";
        try (PreparedStatement pStmt = conn.prepareStatement(query)) {
            pStmt.setString(1, username);

            try (ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("user_id");
                }
                else {
                    throw new UserNotFoundException("User with username, " + username + ", not found.");
                }
            }
        }
    }

}
