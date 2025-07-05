package com.jmill29.tvtrackerapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.jmill29.tvtrackerapi.dto.UserRequest;
import com.jmill29.tvtrackerapi.service.UserService;

@DisplayName("AuthController Test")
class AuthControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    public AuthControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("registerUser returns success response when registration succeeds")
    void registerUser_success() {
        UserRequest req = new UserRequest("Name", "username", "password", "email@example.com");
        when(userService.registerUser(req)).thenReturn(true);
        ResponseEntity<String> response = authController.registerUser(req);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("User registered successfully.", response.getBody());
        verify(userService).registerUser(req);
    }

    @Test
    @DisplayName("registerUser propagates exception if user already exists")
    void registerUser_userAlreadyExists() {
        UserRequest req = new UserRequest("Name", "username", "password", "email@example.com");
        doThrow(new com.jmill29.tvtrackerapi.exception.UserAlreadyExistsException()).when(userService).registerUser(req);
        assertThrows(com.jmill29.tvtrackerapi.exception.UserAlreadyExistsException.class, () -> authController.registerUser(req));
    }

    @Test
    @DisplayName("registerUser propagates exception if input is invalid")
    void registerUser_invalidInput() {
        UserRequest req = null;
        doThrow(new IllegalArgumentException("Invalid input")).when(userService).registerUser(null);
        assertThrows(IllegalArgumentException.class, () -> authController.registerUser(req));
    }
}
