package com.jmill29.tvtrackerapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.jmill29.tvtrackerapi.enums.WatchStatus;

@DisplayName("UserWatchHistory Model Test")
class UserWatchHistoryTest {
    @Test
    @DisplayName("All-args constructor, getters, and setters")
    void testAllArgsAndAccessors() {
        UserWatchHistory hist = new UserWatchHistory(1, 2, WatchStatus.ALREADY_WATCHED);
        assertEquals(1, hist.getUserId());
        assertEquals(2, hist.getShowId());
        assertEquals(WatchStatus.ALREADY_WATCHED, hist.getStatus());

        hist.setUserId(3);
        hist.setShowId(4);
        hist.setStatus(WatchStatus.CURRENTLY_WATCHING);
        assertEquals(3, hist.getUserId());
        assertEquals(4, hist.getShowId());
        assertEquals(WatchStatus.CURRENTLY_WATCHING, hist.getStatus());
    }

    @Test
    @DisplayName("No-args constructor initializes fields to default/null")
    void testNoArgsConstructor() {
        UserWatchHistory hist = new UserWatchHistory();
        assertEquals(0, hist.getUserId());
        assertEquals(0, hist.getShowId());
        assertNull(hist.getStatus());
    }

    @Test
    @DisplayName("toString returns a non-null string and contains userId")
    void testToString() {
        UserWatchHistory hist = new UserWatchHistory(1, 2, WatchStatus.NOT_WATCHED);
        assertNotNull(hist.toString());
        assertTrue(hist.toString().contains("userId=1"));
    }
}
