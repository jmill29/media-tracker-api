package com.jmill29.tvtrackerapi.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("UserRequest DTO Test")
class UserRequestTest {
    @Test
    @DisplayName("All-args constructor, getters, and setters")
    void testAllArgsAndAccessors() {
        UserRequest dto = new UserRequest("Name", "username", "password", "email@example.com");
        assertEquals("Name", dto.getName());
        assertEquals("username", dto.getUsername());
        assertEquals("password", dto.getPassword());
        assertEquals("email@example.com", dto.getEmail());

        dto.setName("New Name");
        dto.setUsername("newuser");
        dto.setPassword("newpass");
        dto.setEmail("new@example.com");
        assertEquals("New Name", dto.getName());
        assertEquals("newuser", dto.getUsername());
        assertEquals("newpass", dto.getPassword());
        assertEquals("new@example.com", dto.getEmail());
    }

    @Test
    @DisplayName("Getters and setters individually")
    void testIndividualGettersSetters() {
        UserRequest dto = new UserRequest();
        dto.setName("Test Name");
        dto.setUsername("testuser");
        dto.setPassword("testpass");
        dto.setEmail("test@example.com");
        assertEquals("Test Name", dto.getName());
        assertEquals("testuser", dto.getUsername());
        assertEquals("testpass", dto.getPassword());
        assertEquals("test@example.com", dto.getEmail());
    }

    @Test
    @DisplayName("toString returns a non-null string")
    void testToString() {
        UserRequest dto = new UserRequest("Name", "username", "password", "email@example.com");
        assertNotNull(dto.toString());
        assertTrue(dto.toString().contains("username"));
    }

    @Test
    @DisplayName("No-args constructor initializes fields to null")
    void testNoArgsConstructor() {
        UserRequest dto = new UserRequest();
        assertNull(dto.getName());
        assertNull(dto.getUsername());
        assertNull(dto.getPassword());
        assertNull(dto.getEmail());
    }
}
