package com.jmill29.tvtrackerapi.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AuthUtil Test")
class AuthUtilTest {

    @Test
    @DisplayName("extractUsernameFromValidAuthHeader returns username")
    void extractUsernameFromValidAuthHeader_returnsUsername() {
        String username = "testuser";
        String password = "testpass";
        String credentials = username + ":" + password;
        String base64 = java.util.Base64.getEncoder().encodeToString(credentials.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        String header = "Basic " + base64;
        assertEquals(username, AuthUtil.extractUsernameFromAuthHeader(header));
    }

    @Test
    @DisplayName("extractUsernameFromAuthHeader throws on invalid header")
    void extractUsernameFromAuthHeader_invalidHeader_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> AuthUtil.extractUsernameFromAuthHeader(null));
        assertThrows(IllegalArgumentException.class, () -> AuthUtil.extractUsernameFromAuthHeader("Bearer sometoken"));
        assertThrows(IllegalArgumentException.class, () -> AuthUtil.extractUsernameFromAuthHeader(""));
    }

    @Test
    @DisplayName("extractUsernameFromAuthHeader throws on malformed Base64")
    void extractUsernameFromAuthHeader_malformedBase64_throwsException() {
        String header = "Basic notbase64!";
        assertThrows(IllegalArgumentException.class, () -> AuthUtil.extractUsernameFromAuthHeader(header));
    }

    @Test
    @DisplayName("extractUsernameFromAuthHeader returns whole string if colon missing")
    void extractUsernameFromAuthHeader_colonMissing_returnsWholeString() {
        String credentials = "usernameonly";
        String base64 = java.util.Base64.getEncoder().encodeToString(credentials.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        String header = "Basic " + base64;
        assertEquals("usernameonly", AuthUtil.extractUsernameFromAuthHeader(header));
    }
}
