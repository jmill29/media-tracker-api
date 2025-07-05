package com.jmill29.tvtrackerapi.controller;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.jmill29.tvtrackerapi.dto.UserWatchHistoryRequest;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryResponse;
import com.jmill29.tvtrackerapi.service.UserWatchHistoryService;
import com.jmill29.tvtrackerapi.utils.AuthUtil;

@DisplayName("UserWatchHistoryController Test")
class UserWatchHistoryControllerTest {
    @Mock
    private UserWatchHistoryService userWatchHistoryService;
    @InjectMocks
    private UserWatchHistoryController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getWatchHistoryByUsername returns list")
    void getWatchHistoryByUsername_returnsList() {
        String username = "testuser";
        String authHeader = "Bearer token";
        List<UserWatchHistoryResponse> mockList = List.of(mock(UserWatchHistoryResponse.class));
        try (org.mockito.MockedStatic<AuthUtil> mocked = mockStatic(AuthUtil.class)) {
            mocked.when(() -> AuthUtil.extractUsernameFromAuthHeader(authHeader)).thenReturn(username);
            when(userWatchHistoryService.getWatchHistoryByUsername(username, false)).thenReturn(mockList);
            ResponseEntity<List<UserWatchHistoryResponse>> response = controller.getWatchHistoryByUsername(false, authHeader);
            assertEquals(200, response.getStatusCode().value());
            assertEquals(mockList, response.getBody());
        }
    }

    @Test
    @DisplayName("addShowToWatchHistory returns ok")
    void addShowToWatchHistory_returnsOk() {
        String username = "testuser";
        String authHeader = "Bearer token";
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        try (org.mockito.MockedStatic<AuthUtil> mocked = mockStatic(AuthUtil.class)) {
            mocked.when(() -> AuthUtil.extractUsernameFromAuthHeader(authHeader)).thenReturn(username);
            when(userWatchHistoryService.addShowToWatchHistory(req, username)).thenReturn(true);
            ResponseEntity<String> response = controller.addShowToWatchHistory(req, authHeader);
            assertEquals(200, response.getStatusCode().value());
            assertEquals("Show added to watch history successfully.", response.getBody());
        }
    }

    @Test
    @DisplayName("updateWatchStatus returns ok")
    void updateWatchStatus_returnsOk() {
        String username = "testuser";
        String authHeader = "Bearer token";
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        try (org.mockito.MockedStatic<AuthUtil> mocked = mockStatic(AuthUtil.class)) {
            mocked.when(() -> AuthUtil.extractUsernameFromAuthHeader(authHeader)).thenReturn(username);
            when(userWatchHistoryService.updateWatchStatus(req, username)).thenReturn(true);
            ResponseEntity<String> response = controller.updateWatchStatus(req, authHeader);
            assertEquals(200, response.getStatusCode().value());
            assertEquals("Watch status updated successfully.", response.getBody());
        }
    }

    @Test
    @DisplayName("deleteShowFromWatchHistory returns ok")
    void deleteShowFromWatchHistory_returnsOk() {
        String username = "testuser";
        String authHeader = "Bearer token";
        int showId = 1;
        try (org.mockito.MockedStatic<AuthUtil> mocked = mockStatic(AuthUtil.class)) {
            mocked.when(() -> AuthUtil.extractUsernameFromAuthHeader(authHeader)).thenReturn(username);
            when(userWatchHistoryService.deleteShowFromWatchHistory(username, showId)).thenReturn(true);
            ResponseEntity<String> response = controller.deleteShowFromWatchHistory(showId, authHeader);
            assertEquals(200, response.getStatusCode().value());
            assertEquals("Show removed from watch history successfully.", response.getBody());
        }
    }

    @Test
    @DisplayName("getWatchHistoryByUsername returns empty list")
    void getWatchHistoryByUsername_returnsEmptyList() {
        String username = "testuser";
        String authHeader = "Bearer token";
        List<UserWatchHistoryResponse> emptyList = List.of();
        try (org.mockito.MockedStatic<AuthUtil> mocked = mockStatic(AuthUtil.class)) {
            mocked.when(() -> AuthUtil.extractUsernameFromAuthHeader(authHeader)).thenReturn(username);
            when(userWatchHistoryService.getWatchHistoryByUsername(username, false)).thenReturn(emptyList);
            ResponseEntity<List<UserWatchHistoryResponse>> response = controller.getWatchHistoryByUsername(false, authHeader);
            assertEquals(200, response.getStatusCode().value());
            assertEquals(emptyList, response.getBody());
        }
    }

    @Test
    @DisplayName("getWatchHistoryByUsername unauthorized")
    void getWatchHistoryByUsername_unauthorized() {
        String authHeader = null;
        try (org.mockito.MockedStatic<AuthUtil> mocked = mockStatic(AuthUtil.class)) {
            mocked.when(() -> AuthUtil.extractUsernameFromAuthHeader(authHeader)).thenThrow(new RuntimeException("Unauthorized"));
            RuntimeException ex = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () ->
                controller.getWatchHistoryByUsername(false, authHeader)
            );
            assertEquals("Unauthorized", ex.getMessage());
        }
    }

    @Test
    @DisplayName("addShowToWatchHistory duplicate")
    void addShowToWatchHistory_duplicate() {
        String username = "testuser";
        String authHeader = "Bearer token";
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        try (org.mockito.MockedStatic<AuthUtil> mocked = mockStatic(AuthUtil.class)) {
            mocked.when(() -> AuthUtil.extractUsernameFromAuthHeader(authHeader)).thenReturn(username);
            when(userWatchHistoryService.addShowToWatchHistory(req, username)).thenThrow(new RuntimeException("Show already in watch history"));
            RuntimeException ex = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () ->
                controller.addShowToWatchHistory(req, authHeader)
            );
            assertEquals("Show already in watch history", ex.getMessage());
        }
    }

    @Test
    @DisplayName("addShowToWatchHistory invalid request")
    void addShowToWatchHistory_invalidRequest() {
        String authHeader = "Bearer token";
        UserWatchHistoryRequest req = null;
        String username = "testuser";
        try (org.mockito.MockedStatic<AuthUtil> mocked = mockStatic(AuthUtil.class)) {
            mocked.when(() -> AuthUtil.extractUsernameFromAuthHeader(authHeader)).thenReturn(username);
            when(userWatchHistoryService.addShowToWatchHistory(req, username)).thenThrow(new IllegalArgumentException("Invalid request body"));
            IllegalArgumentException ex = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () ->
                controller.addShowToWatchHistory(req, authHeader)
            );
            assertEquals("Invalid request body", ex.getMessage());
        }
    }

    @Test
    @DisplayName("updateWatchStatus not found")
    void updateWatchStatus_notFound() {
        String username = "testuser";
        String authHeader = "Bearer token";
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        try (org.mockito.MockedStatic<AuthUtil> mocked = mockStatic(AuthUtil.class)) {
            mocked.when(() -> AuthUtil.extractUsernameFromAuthHeader(authHeader)).thenReturn(username);
            when(userWatchHistoryService.updateWatchStatus(req, username)).thenThrow(new RuntimeException("Watch history entry not found"));
            RuntimeException ex = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () ->
                controller.updateWatchStatus(req, authHeader)
            );
            assertEquals("Watch history entry not found", ex.getMessage());
        }
    }

    @Test
    @DisplayName("deleteShowFromWatchHistory not found")
    void deleteShowFromWatchHistory_notFound() {
        String username = "testuser";
        String authHeader = "Bearer token";
        int showId = 1;
        try (org.mockito.MockedStatic<AuthUtil> mocked = mockStatic(AuthUtil.class)) {
            mocked.when(() -> AuthUtil.extractUsernameFromAuthHeader(authHeader)).thenReturn(username);
            when(userWatchHistoryService.deleteShowFromWatchHistory(username, showId)).thenThrow(new RuntimeException("Show not found in watch history"));
            RuntimeException ex = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () ->
                controller.deleteShowFromWatchHistory(showId, authHeader)
            );
            assertEquals("Show not found in watch history", ex.getMessage());
        }
    }

    @Test
    @DisplayName("deleteShowFromWatchHistory invalid show id")
    void deleteShowFromWatchHistory_invalidShowId() {
        String username = "testuser";
        String authHeader = "Bearer token";
        int showId = -1;
        try (org.mockito.MockedStatic<AuthUtil> mocked = mockStatic(AuthUtil.class)) {
            mocked.when(() -> AuthUtil.extractUsernameFromAuthHeader(authHeader)).thenReturn(username);
            when(userWatchHistoryService.deleteShowFromWatchHistory(username, showId)).thenThrow(new IllegalArgumentException("Invalid show ID"));
            IllegalArgumentException ex = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () ->
                controller.deleteShowFromWatchHistory(showId, authHeader)
            );
            assertEquals("Invalid show ID", ex.getMessage());
        }
    }
}
