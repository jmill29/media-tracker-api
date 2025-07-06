package com.jmill29.tvtrackerapi.service;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.jmill29.tvtrackerapi.dao.UserWatchHistoryDao;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryRequest;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryResponse;
import com.jmill29.tvtrackerapi.exception.ShowNotFoundException;
import com.jmill29.tvtrackerapi.exception.UserNotFoundException;
import com.jmill29.tvtrackerapi.exception.WatchHistoryAlreadyExistsException;
import com.jmill29.tvtrackerapi.exception.WatchHistoryNotFoundException;

@DisplayName("UserWatchHistoryServiceImpl Test")
class UserWatchHistoryServiceImplTest {
    @Mock
    private UserWatchHistoryDao userWatchHistoryDao;
    @Mock
    private UserService userService;
    @Mock
    private ShowService showService;
    @InjectMocks
    private UserWatchHistoryServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("addShowToWatchHistory adds successfully")
    void addShowToWatchHistory_addsSuccessfully() throws Exception {
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        req.setShowId(1);
        when(userService.findByUsername("testuser")).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.dto.UserResponse.class)));
        when(userWatchHistoryDao.isShowInWatchHistory("testuser", 1)).thenReturn(false);
        when(showService.findById(1)).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.model.Show.class)));
        when(userWatchHistoryDao.addShowToWatchHistory(req, "testuser")).thenReturn(true);
        assertTrue(service.addShowToWatchHistory(req, "testuser"));
    }

    @Test
    @DisplayName("addShowToWatchHistory throws if duplicate")
    void addShowToWatchHistory_throwsIfDuplicate() throws Exception {
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        when(userService.findByUsername("testuser")).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.dto.UserResponse.class)));
        when(userWatchHistoryDao.isShowInWatchHistory("testuser", 1)).thenReturn(true);
        req.setShowId(1);
        WatchHistoryAlreadyExistsException ex1 = assertThrows(WatchHistoryAlreadyExistsException.class, () -> service.addShowToWatchHistory(req, "testuser"));
        assertNotNull(ex1);
    }

    @Test
    @DisplayName("addShowToWatchHistory throws if null")
    void addShowToWatchHistory_throwsIfNull() {
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> service.addShowToWatchHistory(null, "testuser"));
        assertNotNull(ex2);
    }

    @Test
    @DisplayName("addShowToWatchHistory throws if show not found")
    void addShowToWatchHistory_throwsIfShowNotFound() throws Exception {
        // Arrange
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        req.setShowId(999);

        // Simulate that the user exists
        when(userService.findByUsername("testuser"))
            .thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.dto.UserResponse.class)));

        // Simulate that the show is not already in watch history
        when(userWatchHistoryDao.isShowInWatchHistory("testuser", 999)).thenReturn(false);

        // Simulate that the show does not exist in the system
        when(showService.findById(999)).thenReturn(java.util.Optional.empty());

        // Act + Assert
        assertThrows(ShowNotFoundException.class, () -> service.addShowToWatchHistory(req, "testuser"));
    }

    @Test
    @DisplayName("updateWatchStatus updates successfully")
    void updateWatchStatus_updatesSuccessfully() throws Exception {
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        req.setShowId(1);
        when(userService.findByUsername("testuser")).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.dto.UserResponse.class)));
        when(userWatchHistoryDao.isShowInWatchHistory("testuser", 1)).thenReturn(true);
        when(showService.findById(1)).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.model.Show.class)));
        when(userWatchHistoryDao.updateWatchStatus(req, "testuser")).thenReturn(true);
        assertTrue(service.updateWatchStatus(req, "testuser"));
    }

    @Test
    @DisplayName("updateWatchStatus throws if not found")
    void updateWatchStatus_throwsIfNotFound() throws Exception {
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        when(userService.findByUsername("testuser")).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.dto.UserResponse.class)));
        when(userWatchHistoryDao.isShowInWatchHistory("testuser", 1)).thenReturn(false);
        when(showService.findById(1)).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.model.Show.class)));
        req.setShowId(1);
        WatchHistoryNotFoundException ex3 = assertThrows(WatchHistoryNotFoundException.class, () -> service.updateWatchStatus(req, "testuser"));
        assertNotNull(ex3);
    }

    @Test
    @DisplayName("updateWatchStatus throws if null")
    void updateWatchStatus_throwsIfNull() {
        IllegalArgumentException ex4 = assertThrows(IllegalArgumentException.class, () -> service.updateWatchStatus(null, "testuser"));
        assertNotNull(ex4);
    }

    @Test
    @DisplayName("updateWatchStatus throws ShowNotFoundException if show does not exist")
    void updateWatchStatus_throwsIfShowNotFound() throws Exception {
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        req.setShowId(999);
        when(userService.findByUsername("testuser")).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.dto.UserResponse.class)));
        when(userWatchHistoryDao.isShowInWatchHistory("testuser", 999)).thenReturn(true);
        when(showService.findById(999)).thenReturn(java.util.Optional.empty());
        assertThrows(ShowNotFoundException.class, () -> service.updateWatchStatus(req, "testuser"));
    }

    @Test
    @DisplayName("deleteShowFromWatchHistory deletes successfully")
    void deleteShowFromWatchHistory_deletesSuccessfully() throws Exception {
        when(userService.findByUsername("testuser")).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.dto.UserResponse.class)));
        when(userWatchHistoryDao.isShowInWatchHistory("testuser", 1)).thenReturn(true);
        when(showService.findById(1)).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.model.Show.class)));
        when(userWatchHistoryDao.deleteShowFromWatchHistory("testuser", 1)).thenReturn(true);
        assertTrue(service.deleteShowFromWatchHistory("testuser", 1));
    }

    @Test
    @DisplayName("deleteShowFromWatchHistory throws if not found")
    void deleteShowFromWatchHistory_throwsIfNotFound() throws Exception {
        when(userService.findByUsername("testuser")).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.dto.UserResponse.class)));
        when(userWatchHistoryDao.isShowInWatchHistory("testuser", 1)).thenReturn(false);
        when(showService.findById(1)).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.model.Show.class)));
        WatchHistoryNotFoundException ex5 = assertThrows(WatchHistoryNotFoundException.class, () -> service.deleteShowFromWatchHistory("testuser", 1));
        assertNotNull(ex5);
    }

    @Test
    @DisplayName("deleteShowFromWatchHistory throws ShowNotFoundException if show does not exist")
    void deleteShowFromWatchHistory_throwsIfShowNotFound() throws Exception {
        when(userService.findByUsername("testuser")).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.dto.UserResponse.class)));
        when(showService.findById(999)).thenReturn(java.util.Optional.empty());
        assertThrows(ShowNotFoundException.class, () -> service.deleteShowFromWatchHistory("testuser", 999));
    }

    @Test
    @DisplayName("isShowInWatchHistory returns true or false")
    void isShowInWatchHistory_returnsTrueOrFalse() throws Exception {
        when(userService.findByUsername("testuser")).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.dto.UserResponse.class)));
        when(userWatchHistoryDao.isShowInWatchHistory("testuser", 1)).thenReturn(true);
        assertTrue(service.isShowInWatchHistory("testuser", 1));
        when(userWatchHistoryDao.isShowInWatchHistory("testuser", 2)).thenReturn(false);
        assertFalse(service.isShowInWatchHistory("testuser", 2));
    }


    @Test
    @DisplayName("getWatchHistoryByUserId returns list")
    void getWatchHistoryByUserId_returnsList() throws Exception {
        List<UserWatchHistoryResponse> mockList = Collections.singletonList(mock(UserWatchHistoryResponse.class));
        when(userService.findById(1)).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.dto.UserResponse.class)));
        when(userWatchHistoryDao.getWatchHistoryByUserId(1, false)).thenReturn(mockList);
        assertEquals(mockList, service.getWatchHistoryByUserId(1, false));
    }


    @Test
    @DisplayName("getWatchHistoryByUsername returns list")
    void getWatchHistoryByUsername_returnsList() throws Exception {
        List<UserWatchHistoryResponse> mockList = Collections.singletonList(mock(UserWatchHistoryResponse.class));
        when(userService.findByUsername("testuser")).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.dto.UserResponse.class)));
        when(userWatchHistoryDao.getWatchHistoryByUsername("testuser", false)).thenReturn(mockList);
        assertEquals(mockList, service.getWatchHistoryByUsername("testuser", false));
    }

    @Test
    @DisplayName("addShowToWatchHistory throws if user not found")
    void addShowToWatchHistory_throwsIfUserNotFound() {
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        when(userService.findByUsername("nouser")).thenReturn(java.util.Optional.empty());
        UserNotFoundException ex6 = assertThrows(UserNotFoundException.class, () -> service.addShowToWatchHistory(req, "nouser"));
        assertNotNull(ex6);
    }

    @Test
    @DisplayName("updateWatchStatus throws if user not found")
    void updateWatchStatus_throwsIfUserNotFound() {
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        when(userService.findByUsername("nouser")).thenReturn(java.util.Optional.empty());
        UserNotFoundException ex7 = assertThrows(UserNotFoundException.class, () -> service.updateWatchStatus(req, "nouser"));
        assertNotNull(ex7);
    }

    @Test
    @DisplayName("deleteShowFromWatchHistory throws if user not found")
    void deleteShowFromWatchHistory_throwsIfUserNotFound() {
        when(userService.findByUsername("nouser")).thenReturn(java.util.Optional.empty());
        UserNotFoundException ex8 = assertThrows(UserNotFoundException.class, () -> service.deleteShowFromWatchHistory("nouser", 1));
        assertNotNull(ex8);
    }

    @Test
    @DisplayName("addShowToWatchHistory propagates DAO exception")
    void addShowToWatchHistory_propagatesDaoException() throws Exception {
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        when(userService.findByUsername("testuser")).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.dto.UserResponse.class)));
        when(userWatchHistoryDao.isShowInWatchHistory("testuser", 1)).thenReturn(false);
        when(userWatchHistoryDao.addShowToWatchHistory(req, "testuser")).thenThrow(new RuntimeException("DB error"));
        RuntimeException ex9 = assertThrows(RuntimeException.class, () -> service.addShowToWatchHistory(req, "testuser"));
        assertNotNull(ex9);
    }

    @Test
    @DisplayName("updateWatchStatus propagates DAO exception")
    void updateWatchStatus_propagatesDaoException() throws Exception {
        UserWatchHistoryRequest req = new UserWatchHistoryRequest();
        when(userService.findByUsername("testuser")).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.dto.UserResponse.class)));
        when(userWatchHistoryDao.isShowInWatchHistory("testuser", 1)).thenReturn(true);
        when(userWatchHistoryDao.updateWatchStatus(req, "testuser")).thenThrow(new RuntimeException("DB error"));
        RuntimeException ex10 = assertThrows(RuntimeException.class, () -> service.updateWatchStatus(req, "testuser"));
        assertNotNull(ex10);
    }

    @Test
    @DisplayName("deleteShowFromWatchHistory propagates DAO exception")
    void deleteShowFromWatchHistory_propagatesDaoException() throws Exception {
        when(userService.findByUsername("testuser")).thenReturn(java.util.Optional.of(mock(com.jmill29.tvtrackerapi.dto.UserResponse.class)));
        when(userWatchHistoryDao.isShowInWatchHistory("testuser", 1)).thenReturn(true);
        when(userWatchHistoryDao.deleteShowFromWatchHistory("testuser", 1)).thenThrow(new RuntimeException("DB error"));
        RuntimeException ex11 = assertThrows(RuntimeException.class, () -> service.deleteShowFromWatchHistory("testuser", 1));
        assertNotNull(ex11);
    }


    @Test
    @DisplayName("getWatchHistoryByUserId propagates DAO exception")
    void getWatchHistoryByUserId_propagatesDaoException() throws Exception {
        when(userWatchHistoryDao.getWatchHistoryByUserId(1, false)).thenThrow(new RuntimeException("DB error"));
        RuntimeException ex12 = assertThrows(RuntimeException.class, () -> service.getWatchHistoryByUserId(1, false));
        assertNotNull(ex12);
    }

    @Test
    @DisplayName("getWatchHistoryByUsername propagates DAO exception")
    void getWatchHistoryByUsername_propagatesDaoException() throws Exception {
        when(userWatchHistoryDao.getWatchHistoryByUsername("testuser", false)).thenThrow(new RuntimeException("DB error"));
        RuntimeException ex13 = assertThrows(RuntimeException.class, () -> service.getWatchHistoryByUsername("testuser", false));
        assertNotNull(ex13);
    }
}
