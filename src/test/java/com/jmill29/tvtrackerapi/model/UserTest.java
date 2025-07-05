package com.jmill29.tvtrackerapi.model;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User Model Test")
class UserTest {
    @Test
    @DisplayName("All-args constructor, getters, and setters")
    void testAllArgsAndAccessors() {
        LocalDateTime now = LocalDateTime.now();
        User user = new User(1, "Name", "username", "pass", "email@example.com", now);
        assertEquals(1, user.getUserId());
        assertEquals("Name", user.getName());
        assertEquals("username", user.getUsername());
        assertEquals("pass", user.getPassword());
        assertEquals("email@example.com", user.getEmail());
        assertEquals(now, user.getCreatedAt());

        user.setUserId(2);
        user.setName("New Name");
        user.setUsername("newuser");
        user.setPassword("newpass");
        user.setEmail("new@example.com");
        LocalDateTime later = now.plusDays(1);
        user.setCreatedAt(later);
        assertEquals(2, user.getUserId());
        assertEquals("New Name", user.getName());
        assertEquals("newuser", user.getUsername());
        assertEquals("newpass", user.getPassword());
        assertEquals("new@example.com", user.getEmail());
        assertEquals(later, user.getCreatedAt());
    }

    @Test
    @DisplayName("No-args constructor initializes fields to default/null")
    void testNoArgsConstructor() {
        User user = new User();
        assertEquals(0, user.getUserId());
        assertNull(user.getName());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getCreatedAt());
    }

    @Test
    @DisplayName("toString returns a non-null string and contains username")
    void testToString() {
        User user = new User(1, "Name", "username", "pass", "email@example.com", LocalDateTime.now());
        assertNotNull(user.toString());
        assertTrue(user.toString().contains("username"));
    }
}
