package com.jmill29.tvtrackerapi.dto;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("UserResponse DTO Test")
class UserResponseTest {
    @Test
    @DisplayName("All-args constructor, getters, and setters")
    void testAllArgsAndAccessors() {
        LocalDateTime now = LocalDateTime.now();
        UserResponse dto = new UserResponse(1, "Name", "username", "email@example.com", now);
        assertEquals(1, dto.getUserId());
        assertEquals("Name", dto.getName());
        assertEquals("username", dto.getUsername());
        assertEquals("email@example.com", dto.getEmail());
        assertEquals(now, dto.getCreatedAt());

        dto.setUserId(2);
        dto.setName("New Name");
        dto.setUsername("newuser");
        dto.setEmail("new@example.com");
        LocalDateTime later = now.plusDays(1);
        dto.setCreatedAt(later);
        assertEquals(2, dto.getUserId());
        assertEquals("New Name", dto.getName());
        assertEquals("newuser", dto.getUsername());
        assertEquals("new@example.com", dto.getEmail());
        assertEquals(later, dto.getCreatedAt());
    }

    @Test
    @DisplayName("Getters and setters individually")
    void testIndividualGettersSetters() {
        UserResponse dto = new UserResponse();
        dto.setUserId(10);
        dto.setName("Test Name");
        dto.setUsername("testuser");
        dto.setEmail("test@example.com");
        LocalDateTime now = LocalDateTime.now();
        dto.setCreatedAt(now);
        assertEquals(10, dto.getUserId());
        assertEquals("Test Name", dto.getName());
        assertEquals("testuser", dto.getUsername());
        assertEquals("test@example.com", dto.getEmail());
        assertEquals(now, dto.getCreatedAt());
    }

    @Test
    @DisplayName("toString returns a non-null string")
    void testToString() {
        LocalDateTime now = LocalDateTime.now();
        UserResponse dto = new UserResponse(5, "Name", "username", "email@example.com", now);
        assertNotNull(dto.toString());
        assertTrue(dto.toString().contains("username"));
    }

    @Test
    @DisplayName("No-args constructor initializes fields to default/null")
    void testNoArgsConstructor() {
        UserResponse dto = new UserResponse();
        assertEquals(0, dto.getUserId());
        assertNull(dto.getName());
        assertNull(dto.getUsername());
        assertNull(dto.getEmail());
        assertNull(dto.getCreatedAt());
    }
}
