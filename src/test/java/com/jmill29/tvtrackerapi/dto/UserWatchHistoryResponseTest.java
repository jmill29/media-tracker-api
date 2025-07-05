package com.jmill29.tvtrackerapi.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("UserWatchHistoryResponse DTO Test")
class UserWatchHistoryResponseTest {
    @Test
    @DisplayName("All-args constructor, getters, and setters")
    void testAllArgsAndAccessors() {
        UserWatchHistoryResponse dto = new UserWatchHistoryResponse(1, "Show Name", "Desc", "img.png", "Watched");
        assertEquals(1, dto.getShowId());
        assertEquals("Show Name", dto.getShowName());
        assertEquals("Desc", dto.getDescription());
        assertEquals("img.png", dto.getImageUrl());
        assertEquals("Watched", dto.getStatus());

        dto.setShowId(2);
        dto.setShowName("New Show");
        dto.setDescription("New Desc");
        dto.setImageUrl("newimg.png");
        dto.setStatus("Currently Watching");
        assertEquals(2, dto.getShowId());
        assertEquals("New Show", dto.getShowName());
        assertEquals("New Desc", dto.getDescription());
        assertEquals("newimg.png", dto.getImageUrl());
        assertEquals("Currently Watching", dto.getStatus());
    }

    @Test
    @DisplayName("Getters and setters individually")
    void testIndividualGettersSetters() {
        UserWatchHistoryResponse dto = new UserWatchHistoryResponse();
        dto.setShowId(10);
        dto.setShowName("Test Show");
        dto.setDescription("Test Desc");
        dto.setImageUrl("test.png");
        dto.setStatus("Not Watched");
        assertEquals(10, dto.getShowId());
        assertEquals("Test Show", dto.getShowName());
        assertEquals("Test Desc", dto.getDescription());
        assertEquals("test.png", dto.getImageUrl());
        assertEquals("Not Watched", dto.getStatus());
    }

    @Test
    @DisplayName("toString returns a non-null string")
    void testToString() {
        UserWatchHistoryResponse dto = new UserWatchHistoryResponse(5, "Show", "Desc", "img.png", "Watched");
        assertNotNull(dto.toString());
        assertTrue(dto.toString().contains("Show"));
    }

    @Test
    @DisplayName("No-args constructor initializes fields to default/null")
    void testNoArgsConstructor() {
        UserWatchHistoryResponse dto = new UserWatchHistoryResponse();
        assertEquals(0, dto.getShowId());
        assertNull(dto.getShowName());
        assertNull(dto.getDescription());
        assertNull(dto.getImageUrl());
        assertNull(dto.getStatus());
    }
}
