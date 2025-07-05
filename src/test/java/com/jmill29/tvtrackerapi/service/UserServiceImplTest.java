package com.jmill29.tvtrackerapi.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.jmill29.tvtrackerapi.dao.UserDao;
import com.jmill29.tvtrackerapi.dto.UserResponse;
import com.jmill29.tvtrackerapi.exception.DatabaseException;
import com.jmill29.tvtrackerapi.model.User;

@DisplayName("UserServiceImpl Test")
class UserServiceImplTest {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findById returns user for valid id")
    void findById_returnsUser() throws Exception {
        UserResponse user = new UserResponse(1, "Test User", "testuser", "test@example.com", null);
        when(userDao.findById(1)).thenReturn(Optional.of(user));
        Optional<UserResponse> result = userService.findById(1);
        assertTrue(result.isPresent());
        assertEquals("testuser", result.get().getUsername());
    }

    @Test
    @DisplayName("findById throws IllegalArgumentException if id is invalid")
    void findById_throwsIfInvalid() {
        assertThrows(IllegalArgumentException.class, () -> userService.findById(0));
    }

    @Test
    @DisplayName("findByUsername returns user for valid username")
    void findByUsername_returnsUser() throws Exception {
        UserResponse user = new UserResponse(1, "Test User", "testuser", "test@example.com", null);
        when(userDao.findByUsername("testuser")).thenReturn(Optional.of(user));
        Optional<UserResponse> result = userService.findByUsername("testuser");
        assertTrue(result.isPresent());
        assertEquals("testuser", result.get().getUsername());
    }

    @Test
    @DisplayName("findByUsername throws IllegalArgumentException if username is invalid")
    void findByUsername_throwsIfInvalid() {
        assertThrows(IllegalArgumentException.class, () -> userService.findByUsername(""));
    }

    @Test
    @DisplayName("findAll returns users")
    void findAll_returnsUsers() throws Exception {
        when(userDao.findAll()).thenReturn(List.of(new UserResponse()));
        assertFalse(userService.findAll().isEmpty());
    }

    @Test
    @DisplayName("findAll throws DatabaseException on DB error")
    void findAll_throwsIfDBError() throws Exception {
        when(userDao.findAll()).thenThrow(new com.jmill29.tvtrackerapi.exception.DatabaseException("DB error"));
        assertThrows(DatabaseException.class, () -> userService.findAll());
    }

    @Test
    @DisplayName("save saves user successfully")
    void save_savesSuccessfully() throws Exception {
        User user = new User();
        when(userDao.save(user)).thenReturn(true);
        assertTrue(userService.save(user));
    }

    @Test
    @DisplayName("save throws IllegalArgumentException if user is null")
    void save_throwsIfNull() {
        assertThrows(IllegalArgumentException.class, () -> userService.save(null));
    }

    @Test
    @DisplayName("save throws DatabaseException on DB error")
    void save_throwsDatabaseException() throws Exception {
        User user = new User();
        when(userDao.save(user)).thenThrow(new java.sql.SQLException("DB error"));
        assertThrows(DatabaseException.class, () -> userService.save(user));
    }

    @Test
    @DisplayName("deleteById deletes user successfully")
    void deleteById_deletesSuccessfully() throws Exception {
        when(userDao.deleteById(1)).thenReturn(true);
        assertTrue(userService.deleteById(1));
    }

    @Test
    @DisplayName("deleteById throws IllegalArgumentException if id is invalid")
    void deleteById_throwsIfIdInvalid() {
        assertThrows(IllegalArgumentException.class, () -> userService.deleteById(0));
    }

    @Test
    @DisplayName("deleteById throws DatabaseException on DB error")
    void deleteById_throwsDatabaseException() throws Exception {
        when(userDao.deleteById(1)).thenThrow(new java.sql.SQLException("DB error"));
        assertThrows(DatabaseException.class, () -> userService.deleteById(1));
    }
}
