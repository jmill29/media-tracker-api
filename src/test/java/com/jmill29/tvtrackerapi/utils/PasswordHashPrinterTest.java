package com.jmill29.tvtrackerapi.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@DisplayName("PasswordHashPrinter Test")
class PasswordHashPrinterTest {

    @Test
    @DisplayName("BCryptPasswordEncoder hashes and verifies password")
    void bCryptPasswordEncoder_hashesPassword() {
        String rawPassword = "test123";
        String hash = new BCryptPasswordEncoder().encode(rawPassword);
        assertNotNull(hash);
        assertTrue(hash.startsWith("$2a$") || hash.startsWith("$2b$") || hash.startsWith("$2y$"));
        assertTrue(new BCryptPasswordEncoder().matches(rawPassword, hash));
    }
}
