package com.jmill29.tvtrackerapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.jmill29.tvtrackerapi.dto.UserResponse;
import com.jmill29.tvtrackerapi.exception.UserAlreadyExistsException;
import com.jmill29.tvtrackerapi.exception.UserNotFoundException;
import com.jmill29.tvtrackerapi.model.User;

@Repository
public class UserDaoImpl implements UserDao {

    private final DataSource dataSource;
    private final PasswordEncoder encoder;

    @Autowired
    public UserDaoImpl(DataSource dataSource, PasswordEncoder encoder) {
        this.dataSource = dataSource;
        this.encoder = encoder;
    }

    @Override
    public Optional<UserResponse> findById(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(query);
            pStmt.setInt(1, id);

            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapUser(rs));
            }
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserResponse> findByUsername(String username) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT user_id, name, username, email, created_at FROM users WHERE username = ?";
            PreparedStatement pStmt = conn.prepareStatement(query);

            pStmt.setString(1, username);

            ResultSet rs = pStmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapUser(rs));
            }
            return Optional.empty();
        }
    }

    @Override
    public List<UserResponse> findAll() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT * FROM users";
            PreparedStatement pStmt = conn.prepareStatement(query);
            ResultSet rs = pStmt.executeQuery();

            List<UserResponse> users = new ArrayList<>();

            while (rs.next()) {
                users.add(mapUser(rs));
            }

            return users;
        }
    }

    @Override
    public boolean save(User user) throws SQLException, UserAlreadyExistsException, UserNotFoundException {
        // If userId is 0, treat as a new user (insert)
        if (user.getUserId() == 0) {
            // Check if username already exists
            if (findByUsername(user.getUsername()).isPresent()) {
                // Username already exists, cannot create new user
                throw new UserAlreadyExistsException("Username already exists: " + user.getUsername());
            }

            try (Connection conn = dataSource.getConnection()) {
                return createUser(user, conn);
            }
        } else {
            // check if user exists by ID
            if (!findById(user.getUserId()).isPresent()) {
                // User with this ID does not exist, cannot update
                throw new UserNotFoundException("User not found with ID: " + user.getUserId());
            }

            try (Connection conn = dataSource.getConnection()) {
                return updateUser(user, conn);
            }
        }
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(query);

            pStmt.setInt(1, id);

            int rowsAffected = pStmt.executeUpdate();

            return rowsAffected > 0;
        }
    }

    private boolean updateUser(User user, Connection conn) throws SQLException {
        // Only update password if a new one is provided (not null/blank)
        boolean updatingPassword = user.getPassword() != null && !user.getPassword().isBlank();

        // Build query dynamically depending on whether password is being updated
        String query = "UPDATE users SET name = ?, username = ?, ";

        if (updatingPassword) {
            query += "password = ?, ";
        }

        query += "email = ? WHERE user_id = ?";

        PreparedStatement pStmt = conn.prepareStatement(query);

        pStmt.setString(1, user.getName());
        pStmt.setString(2, user.getUsername());
        
        int i = 3;
        if (updatingPassword) {
            // Password is encoded before storing
            pStmt.setString(i++, encoder.encode(user.getPassword()));
        }

        pStmt.setString(i++, user.getEmail());
        pStmt.setInt(i, user.getUserId());

        int rowsAffected = pStmt.executeUpdate();

        return rowsAffected > 0;
    }

    private boolean createUser(User user, Connection conn) throws SQLException {
        // Always encode password before storing
        String query = "INSERT INTO users (name, username, password, email) VALUES (?, ?, ?, ?)";
        PreparedStatement pStmt = conn.prepareStatement(query);

        pStmt.setString(1, user.getName());
        pStmt.setString(2, user.getUsername());
        pStmt.setString(3, encoder.encode(user.getPassword()));
        pStmt.setString(4, user.getEmail());

        int rowsAffected = pStmt.executeUpdate();

        return rowsAffected > 0;
    }

    private UserResponse mapUser(ResultSet rs) throws SQLException {
        // Maps a ResultSet row to a UserDto. Order matches UserDto constructor: userId, name, username, email, createdAt
        return new UserResponse(
            rs.getInt("user_id"),
            rs.getString("name"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getTimestamp("created_at").toLocalDateTime()
        );
    }

}
