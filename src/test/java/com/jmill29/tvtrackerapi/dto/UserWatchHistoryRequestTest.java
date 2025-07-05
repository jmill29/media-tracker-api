package com.jmill29.tvtrackerapi.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.jmill29.tvtrackerapi.enums.WatchStatus;

@DisplayName("UserWatchHistoryRequest DTO Test")
class UserWatchHistoryRequestTest {
    @Test
    @DisplayName("All-args constructor, getters, and setters")
    void testAllArgsAndAccessors() {
        UserWatchHistoryRequest dto = new UserWatchHistoryRequest(42, WatchStatus.ALREADY_WATCHED);
        assertEquals(42, dto.getShowId());
        assertEquals(WatchStatus.ALREADY_WATCHED, dto.getStatus());

        dto.setShowId(99);
        dto.setStatus(WatchStatus.CURRENTLY_WATCHING);
        assertEquals(99, dto.getShowId());
        assertEquals(WatchStatus.CURRENTLY_WATCHING, dto.getStatus());
    }

    @Test
    @DisplayName("Getters and setters individually")
    void testIndividualGettersSetters() {
        UserWatchHistoryRequest dto = new UserWatchHistoryRequest();
        dto.setShowId(7);
        dto.setStatus(WatchStatus.NOT_WATCHED);
        assertEquals(7, dto.getShowId());
        assertEquals(WatchStatus.NOT_WATCHED, dto.getStatus());
    }

    @Test
    @DisplayName("toString returns a non-null string")
    void testToString() {
        UserWatchHistoryRequest dto = new UserWatchHistoryRequest(3, WatchStatus.CURRENTLY_WATCHING);
        assertNotNull(dto.toString());
        assertTrue(dto.toString().contains("CURRENTLY_WATCHING") || dto.toString().contains("Currently Watching"));
    }

    @Test
    @DisplayName("No-args constructor initializes fields to default/null")
    void testNoArgsConstructor() {
        UserWatchHistoryRequest dto = new UserWatchHistoryRequest();
        assertEquals(0, dto.getShowId());
        assertNull(dto.getStatus());
    }
}
