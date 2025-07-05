package com.jmill29.tvtrackerapi.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ErrorResponse DTO Test")
class ErrorResponseTest {
    @Test
    @DisplayName("All-args constructor, getters, and setters")
    void testAllArgsAndAccessors() {
        ErrorResponse dto = new ErrorResponse(404, "Not Found", 123456789L);
        assertEquals(404, dto.getStatus());
        assertEquals("Not Found", dto.getMessage());
        assertEquals(123456789L, dto.getTimestamp());

        dto.setStatus(500);
        dto.setMessage("Server Error");
        dto.setTimestamp(987654321L);
        assertEquals(500, dto.getStatus());
        assertEquals("Server Error", dto.getMessage());
        assertEquals(987654321L, dto.getTimestamp());
    }

    @Test
    @DisplayName("Getters and setters individually")
    void testIndividualGettersSetters() {
        ErrorResponse dto = new ErrorResponse();
        dto.setStatus(403);
        dto.setMessage("Forbidden");
        dto.setTimestamp(111111111L);
        assertEquals(403, dto.getStatus());
        assertEquals("Forbidden", dto.getMessage());
        assertEquals(111111111L, dto.getTimestamp());
    }

    @Test
    @DisplayName("toString returns a non-null string")
    void testToString() {
        ErrorResponse dto = new ErrorResponse(400, "Bad Request", 222222222L);
        assertNotNull(dto.toString());
        assertTrue(dto.toString().contains("Bad Request"));
    }

    @Test
    @DisplayName("No-args constructor initializes fields to default/null")
    void testNoArgsConstructor() {
        ErrorResponse dto = new ErrorResponse();
        assertEquals(0, dto.getStatus());
        assertNull(dto.getMessage());
        assertEquals(0L, dto.getTimestamp());
    }
}
