package com.jmill29.tvtrackerapi.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Optional;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.jmill29.tvtrackerapi.dto.UserResponse;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
@ActiveProfiles("test")
@DisplayName("UserDaoImpl Integration Test")
class UserDaoImplIntegrationTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void setUp() throws Exception {
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM users");
            stmt.execute("ALTER TABLE users ALTER COLUMN user_id RESTART WITH 1;");
            stmt.execute("INSERT INTO users (name, username, password, email, enabled) VALUES ('Test User', 'testuser', 'pass', 'test@example.com', TRUE)");
        }
    }

    @Test
    @DisplayName("findById returns user for valid id")
    void findById_returnsUser() throws Exception {
        Optional<UserResponse> user = userDao.findById(1);
        assertTrue(user.isPresent());
        assertEquals("testuser", user.get().getUsername());
    }

    @Test
    @DisplayName("findByUsername returns user for valid username")
    void findByUsername_returnsUser() throws Exception {
        Optional<UserResponse> user = userDao.findByUsername("testuser");
        assertTrue(user.isPresent());
        assertEquals("testuser", user.get().getUsername());
    }

    @Test
    @DisplayName("findById returns empty for missing id")
    void findById_returnsEmptyForMissingId() throws Exception {
        Optional<UserResponse> user = userDao.findById(999);
        assertTrue(user.isEmpty());
    }

    @Test
    @DisplayName("findByUsername returns empty for missing username")
    void findByUsername_returnsEmptyForMissingUsername() throws Exception {
        Optional<UserResponse> user = userDao.findByUsername("nouser");
        assertTrue(user.isEmpty());
    }

    @Test
    @DisplayName("deleteById deletes user")
    void deleteById_deletesUser() throws Exception {
        boolean deleted = userDao.deleteById(1);
        assertTrue(deleted);
        assertTrue(userDao.findById(1).isEmpty());
    }

    @Test
    @DisplayName("deleteById returns false for missing user")
    void deleteById_returnsFalseForMissingUser() throws Exception {
        boolean deleted = userDao.deleteById(999);
        assertFalse(deleted);
    }

    @Test
    @DisplayName("save inserts new user")
    void save_insertsNewUser() throws Exception {
        com.jmill29.tvtrackerapi.model.User newUser = new com.jmill29.tvtrackerapi.model.User(0, "New User", "newuser", "pass", "new@example.com", null);
        boolean result = userDao.save(newUser);
        assertTrue(result);
        assertTrue(userDao.findByUsername("newuser").isPresent());
    }

    @Test
    @DisplayName("save throws on duplicate username")
    void save_throwsOnDuplicateUsername() throws Exception {
        com.jmill29.tvtrackerapi.model.User dup = new com.jmill29.tvtrackerapi.model.User(0, "Test User", "testuser", "pass", "test@example.com", null);
        assertThrows(com.jmill29.tvtrackerapi.exception.UserAlreadyExistsException.class, () -> {
            try {
                userDao.save(dup);
            } catch (java.sql.SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    @DisplayName("save updates existing user")
    void save_updatesExistingUser() throws Exception {
        Optional<UserResponse> userOpt = userDao.findById(1);
        assertTrue(userOpt.isPresent());
        com.jmill29.tvtrackerapi.model.User user = new com.jmill29.tvtrackerapi.model.User(1, "Updated Name", "testuser", "pass", "test@example.com", null);
        boolean result = userDao.save(user);
        assertTrue(result);
        assertEquals("Updated Name", userDao.findById(1).get().getName());
    }

    @Test
    @DisplayName("save throws on update missing user")
    void save_throwsOnUpdateMissingUser() throws Exception {
        com.jmill29.tvtrackerapi.model.User fake = new com.jmill29.tvtrackerapi.model.User(999, "Fake", "fake", "pass", "fake@example.com", null);
        assertThrows(com.jmill29.tvtrackerapi.exception.UserNotFoundException.class, () -> {
            try {
                userDao.save(fake);
            } catch (java.sql.SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
