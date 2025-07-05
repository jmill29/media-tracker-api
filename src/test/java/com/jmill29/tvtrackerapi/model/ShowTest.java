package com.jmill29.tvtrackerapi.model;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Show Model Test")
class ShowTest {
    @Test
    @DisplayName("All-args constructor, getters, and setters")
    void testAllArgsAndAccessors() {
        LocalDateTime now = LocalDateTime.now();
        Show show = new Show(1, "Test Show", "A show desc", "img.png", 10, (short)2020, now);
        assertEquals(1, show.getId());
        assertEquals("Test Show", show.getName());
        assertEquals("A show desc", show.getDescription());
        assertEquals("img.png", show.getImageUrl());
        assertEquals(10, show.getNumEpisodes());
        assertEquals((short)2020, show.getReleaseYear());
        assertEquals(now, show.getCreatedAt());

        show.setId(2);
        show.setName("New Name");
        show.setDescription("New Desc");
        show.setImageUrl("newimg.png");
        show.setNumEpisodes(20);
        show.setReleaseYear((short)2021);
        LocalDateTime later = now.plusDays(1);
        show.setCreatedAt(later);
        assertEquals(2, show.getId());
        assertEquals("New Name", show.getName());
        assertEquals("New Desc", show.getDescription());
        assertEquals("newimg.png", show.getImageUrl());
        assertEquals(20, show.getNumEpisodes());
        assertEquals((short)2021, show.getReleaseYear());
        assertEquals(later, show.getCreatedAt());
    }

    @Test
    @DisplayName("No-args constructor initializes fields to default/null")
    void testNoArgsConstructor() {
        Show show = new Show();
        assertEquals(0, show.getId());
        assertNull(show.getName());
        assertNull(show.getDescription());
        assertNull(show.getImageUrl());
        assertEquals(0, show.getNumEpisodes());
        assertEquals(0, show.getReleaseYear());
        assertNull(show.getCreatedAt());
    }

    @Test
    @DisplayName("toString returns a non-null string and contains name")
    void testToString() {
        Show show = new Show(1, "Test Show", "desc", "img.png", 10, (short)2020, LocalDateTime.now());
        assertNotNull(show.toString());
        assertTrue(show.toString().contains("Test Show"));
    }
}
