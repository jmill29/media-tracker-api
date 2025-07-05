package com.jmill29.tvtrackerapi;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Tvtrackerapi Application Main Test")
class TvtrackerapiApplicationMainTest {
    @Test
    @DisplayName("main runs without exception")
    void main_runsWithoutException() {
        assertDoesNotThrow(() -> TvtrackerapiApplication.main(new String[]{}));
    }
}
