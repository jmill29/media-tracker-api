package com.jmill29.tvtrackerapi.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.jmill29.tvtrackerapi.dto.UserWatchHistoryRequest;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryResponse;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.ANY)
@ActiveProfiles("test")
@DisplayName("UserWatchHistoryDaoImpl Integration Test")
class UserWatchHistoryDaoImplIntegrationTest {

    @Autowired
    private UserWatchHistoryDao userWatchHistoryDao;
    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void setUp() throws Exception {
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM user_watch_history");
            stmt.execute("DELETE FROM users");
            stmt.execute("DELETE FROM tv_shows");
            stmt.execute("INSERT INTO users (user_id, name, username, password, email, enabled) VALUES (1, 'Test User', 'testuser', 'pass', 'test@example.com', TRUE)");
            stmt.execute("INSERT INTO tv_shows (show_id, show_name, description, image_url, num_episodes, release_year) VALUES (1, 'Test Show', 'A test show', 'test.jpg', 10, 2020)");
            // Do not insert into user_watch_history here, so addShowToWatchHistory can insert without PK violation
        }
    }

    @Test
    @DisplayName("getWatchHistoryByUserId returns history")
    void getWatchHistoryByUserId_returnsHistory() throws Exception {
        List<UserWatchHistoryResponse> history = userWatchHistoryDao.getWatchHistoryByUserId(1, true);
        assertFalse(history.isEmpty());
        assertEquals("Test Show", history.get(0).getShowName());
    }

    @Test
    @DisplayName("addShowToWatchHistory adds successfully")
    void addShowToWatchHistory_addsSuccessfully() throws Exception {
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        req.setShowId(1);
        req.setStatus(com.jmill29.tvtrackerapi.enums.WatchStatus.ALREADY_WATCHED);
        boolean result = userWatchHistoryDao.addShowToWatchHistory(req, "testuser");
        assertTrue(result);
    }

    @Test
    @DisplayName("addShowToWatchHistory fails on duplicate")
    void addShowToWatchHistory_duplicateFails() throws Exception {
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        req.setShowId(1);
        req.setStatus(com.jmill29.tvtrackerapi.enums.WatchStatus.ALREADY_WATCHED);
        assertTrue(userWatchHistoryDao.addShowToWatchHistory(req, "testuser"));
        // Try to add again (should fail or throw)
        boolean duplicateResult = false;
        try {
            duplicateResult = userWatchHistoryDao.addShowToWatchHistory(req, "testuser");
        } catch (Exception e) {
            duplicateResult = false;
        }
        assertFalse(duplicateResult);
    }

    @Test
    @DisplayName("updateWatchStatus updates successfully")
    void updateWatchStatus_updatesSuccessfully() throws Exception {
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        req.setShowId(1);
        req.setStatus(com.jmill29.tvtrackerapi.enums.WatchStatus.ALREADY_WATCHED);
        userWatchHistoryDao.addShowToWatchHistory(req, "testuser");
        req.setStatus(com.jmill29.tvtrackerapi.enums.WatchStatus.WANT_TO_WATCH);
        boolean updated = userWatchHistoryDao.updateWatchStatus(req, "testuser");
        assertTrue(updated);
    }

    @Test
    @DisplayName("updateWatchStatus returns false for nonexistent")
    void updateWatchStatus_nonexistentReturnsFalse() throws Exception {
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        req.setShowId(99);
        req.setStatus(com.jmill29.tvtrackerapi.enums.WatchStatus.ALREADY_WATCHED);
        boolean updated = userWatchHistoryDao.updateWatchStatus(req, "testuser");
        assertFalse(updated);
    }

    @Test
    @DisplayName("deleteShowFromWatchHistory deletes successfully")
    void deleteShowFromWatchHistory_deletesSuccessfully() throws Exception {
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        req.setShowId(1);
        req.setStatus(com.jmill29.tvtrackerapi.enums.WatchStatus.ALREADY_WATCHED);
        userWatchHistoryDao.addShowToWatchHistory(req, "testuser");
        boolean deleted = userWatchHistoryDao.deleteShowFromWatchHistory("testuser", 1);
        assertTrue(deleted);
    }

    @Test
    @DisplayName("deleteShowFromWatchHistory returns false for nonexistent")
    void deleteShowFromWatchHistory_nonexistentReturnsFalse() throws Exception {
        boolean deleted = userWatchHistoryDao.deleteShowFromWatchHistory("testuser", 99);
        assertFalse(deleted);
    }

    @Test
    @DisplayName("isShowInWatchHistory returns true if exists")
    void isShowInWatchHistory_returnsTrueIfExists() throws Exception {
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        req.setShowId(1);
        req.setStatus(com.jmill29.tvtrackerapi.enums.WatchStatus.ALREADY_WATCHED);
        userWatchHistoryDao.addShowToWatchHistory(req, "testuser");
        assertTrue(userWatchHistoryDao.isShowInWatchHistory("testuser", 1));
    }

    @Test
    @DisplayName("isShowInWatchHistory returns false if not exists")
    void isShowInWatchHistory_returnsFalseIfNotExists() throws Exception {
        assertFalse(userWatchHistoryDao.isShowInWatchHistory("testuser", 99));
    }
}
