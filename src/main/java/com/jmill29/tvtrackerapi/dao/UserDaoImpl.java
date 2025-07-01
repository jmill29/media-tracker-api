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

import com.jmill29.tvtrackerapi.dto.UserDto;
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
    public Optional<UserDto> findById(int id) throws SQLException {
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
    public Optional<UserDto> findByUsername(String username) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT user_id, name, username, email FROM users WHERE username = ?";
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
    public List<UserDto> findAll() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            String query = "SELECT * FROM users";
            PreparedStatement pStmt = conn.prepareStatement(query);
            ResultSet rs = pStmt.executeQuery();

            List<UserDto> users = new ArrayList<>();

            while (rs.next()) {
                users.add(mapUser(rs));
            }

            return users;
        }
    }

    @Override
    public boolean save(User user) throws SQLException {
        if (user.getUserId() == 0) {
            if (findByUsername(user.getUsername()).isPresent()) {
                throw new UserAlreadyExistsException("User with Username " + user.getUsername() + " already exists");
            }

            try (Connection conn = dataSource.getConnection()) {
                return createUser(user, conn);
            }
        } else {
            if (findById(user.getUserId()).isEmpty()) {
                throw new UserNotFoundException("User with ID " + user.getUserId() + " not found");
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
        boolean updatingPassword = user.getPassword() != null && !user.getPassword().isBlank();

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
            pStmt.setString(i++, encoder.encode(user.getPassword()));
        }

        pStmt.setString(i++, user.getEmail());
        pStmt.setInt(i, user.getUserId());

        int rowsAffected = pStmt.executeUpdate();

        return rowsAffected > 0;
    }

    private boolean createUser(User user, Connection conn) throws SQLException {
        String query = "INSERT INTO users (name, username, password, email) VALUES (?, ?, ?, ?)";
        PreparedStatement pStmt = conn.prepareStatement(query);

        pStmt.setString(1, user.getName());
        pStmt.setString(2, user.getUsername());
        pStmt.setString(3, encoder.encode(user.getPassword()));
        pStmt.setString(4, user.getEmail());

        int rowsAffected = pStmt.executeUpdate();

        return rowsAffected > 0;
    }

    private UserDto mapUser(ResultSet rs) throws SQLException {
        return new UserDto(
            rs.getString("email"),
            rs.getString("name"),
            rs.getInt("user_id"),
            rs.getString("username"),
            rs.getTimestamp("created_at").toLocalDateTime()
            );
    }

}
