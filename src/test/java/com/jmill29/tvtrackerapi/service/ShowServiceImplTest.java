package com.jmill29.tvtrackerapi.service;

import java.util.Collections;
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

import com.jmill29.tvtrackerapi.dao.ShowDao;
import com.jmill29.tvtrackerapi.exception.DatabaseException;
import com.jmill29.tvtrackerapi.exception.NoShowsFoundException;
import com.jmill29.tvtrackerapi.exception.ShowAlreadyExistsException;
import com.jmill29.tvtrackerapi.exception.ShowNotFoundException;
import com.jmill29.tvtrackerapi.model.Show;

@DisplayName("ShowServiceImpl Test")
class ShowServiceImplTest {
    @Mock
    private ShowDao showDao;
    @InjectMocks
    private ShowServiceImpl showService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("findById returns show for valid id")
    void findById_returnsShow() throws Exception {
        Show show = new Show(1, "Test Show", "desc", "img", 10, (short)2020, null);
        when(showDao.findById(1)).thenReturn(Optional.of(show));
        Optional<Show> result = showService.findById(1);
        assertTrue(result.isPresent());
        assertEquals("Test Show", result.get().getName());
    }

    @Test
    @DisplayName("findById throws ShowNotFoundException if not found")
    void findById_throwsIfNotFound() throws Exception {
        when(showDao.findById(1)).thenReturn(Optional.empty());
        assertThrows(ShowNotFoundException.class, () -> showService.findById(1));
    }

    @Test
    @DisplayName("findById throws IllegalArgumentException if id is invalid")
    void findById_throwsIfInvalid() {
        assertThrows(IllegalArgumentException.class, () -> showService.findById(0));
    }

    @Test
    @DisplayName("findAll returns shows")
    void findAll_returnsShows() throws Exception {
        when(showDao.findAll()).thenReturn(List.of(new Show()));
        assertFalse(showService.findAll().isEmpty());
    }

    @Test
    @DisplayName("findAll throws NoShowsFoundException if empty")
    void findAll_throwsIfEmpty() throws Exception {
        when(showDao.findAll()).thenReturn(Collections.emptyList());
        assertThrows(NoShowsFoundException.class, () -> showService.findAll());
    }

    @Test
    @DisplayName("findAll throws DatabaseException on SQL error")
    void findAll_throwsDatabaseException() throws Exception {
        when(showDao.findAll()).thenThrow(new java.sql.SQLException("DB error"));
        assertThrows(DatabaseException.class, () -> showService.findAll());
    }

    @Test
    @DisplayName("findByName returns shows for valid name")
    void findByName_returnsShows() throws Exception {
        Show show = new Show(1, "Test Show", "desc", "img", 10, (short)2020, null);
        when(showDao.findByName("Test Show")).thenReturn(List.of(show));
        List<Show> result = showService.findByName("Test Show");
        assertFalse(result.isEmpty());
        assertEquals("Test Show", result.get(0).getName());
    }

    @Test
    @DisplayName("findByName throws ShowNotFoundException if empty")
    void findByName_throwsIfEmpty() throws Exception {
        when(showDao.findByName("NoShow")).thenReturn(Collections.emptyList());
        assertThrows(ShowNotFoundException.class, () -> showService.findByName("NoShow"));
    }

    @Test
    @DisplayName("findByName throws IllegalArgumentException if null or blank")
    void findByName_throwsIfNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> showService.findByName(null));
        assertThrows(IllegalArgumentException.class, () -> showService.findByName(" "));
    }

    @Test
    @DisplayName("findByName throws DatabaseException on SQL error")
    void findByName_throwsDatabaseException() throws Exception {
        when(showDao.findByName("Test Show")).thenThrow(new java.sql.SQLException("DB error"));
        assertThrows(DatabaseException.class, () -> showService.findByName("Test Show"));
    }

    @Test
    @DisplayName("findByGenre returns shows for valid genre")
    void findByGenre_returnsShows() throws Exception {
        Show show = new Show(1, "Test Show", "desc", "img", 10, (short)2020, null);
        when(showDao.findByGenre("Drama")).thenReturn(List.of(show));
        List<Show> result = showService.findByGenre("Drama");
        assertFalse(result.isEmpty());
        assertEquals("Test Show", result.get(0).getName());
    }

    @Test
    @DisplayName("findByGenre throws NoShowsFoundException if empty")
    void findByGenre_throwsIfEmpty() throws Exception {
        when(showDao.findByGenre("Comedy")).thenReturn(Collections.emptyList());
        assertThrows(NoShowsFoundException.class, () -> showService.findByGenre("Comedy"));
    }

    @Test
    @DisplayName("findByGenre throws IllegalArgumentException if null or blank")
    void findByGenre_throwsIfNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> showService.findByGenre(null));
        assertThrows(IllegalArgumentException.class, () -> showService.findByGenre(" "));
    }

    @Test
    @DisplayName("findByGenre throws DatabaseException on SQL error")
    void findByGenre_throwsDatabaseException() throws Exception {
        when(showDao.findByGenre("Drama")).thenThrow(new java.sql.SQLException("DB error"));
        assertThrows(DatabaseException.class, () -> showService.findByGenre("Drama"));
    }

    @Test
    @DisplayName("save saves show successfully")
    void save_savesSuccessfully() throws Exception {
        Show show = new Show(1, "Test Show", "desc", "img", 10, (short)2020, null);
        when(showDao.save(show)).thenReturn(true);
        assertTrue(showService.save(show));
    }

    @Test
    @DisplayName("save throws IllegalArgumentException if null")
    void save_throwsIfNull() {
        assertThrows(IllegalArgumentException.class, () -> showService.save(null));
    }

    @Test
    @DisplayName("save throws ShowAlreadyExistsException on duplicate")
    void save_throwsShowAlreadyExistsException() throws Exception {
        Show show = new Show(1, "Test Show", "desc", "img", 10, (short)2020, null);
        java.sql.SQLException ex = new java.sql.SQLException("duplicate key");
        when(showDao.save(show)).thenThrow(ex);
        assertThrows(ShowAlreadyExistsException.class, () -> showService.save(show));
    }

    @Test
    @DisplayName("save throws DatabaseException on SQL error")
    void save_throwsDatabaseException() throws Exception {
        Show show = new Show(1, "Test Show", "desc", "img", 10, (short)2020, null);
        java.sql.SQLException ex = new java.sql.SQLException("other error");
        when(showDao.save(show)).thenThrow(ex);
        assertThrows(DatabaseException.class, () -> showService.save(show));
    }

    @Test
    @DisplayName("deleteById deletes show successfully")
    void deleteById_deletesSuccessfully() throws Exception {
        Show show = new Show(1, "Test Show", "desc", "img", 10, (short)2020, null);
        when(showDao.findById(1)).thenReturn(Optional.of(show));
        when(showDao.deleteById(1)).thenReturn(true);
        assertTrue(showService.deleteById(1));
    }

    @Test
    @DisplayName("deleteById throws IllegalArgumentException if id invalid")
    void deleteById_throwsIfIdInvalid() {
        assertThrows(IllegalArgumentException.class, () -> showService.deleteById(0));
    }

    @Test
    @DisplayName("deleteById throws ShowNotFoundException if not found")
    void deleteById_throwsIfNotFound() throws Exception {
        when(showDao.findById(2)).thenReturn(Optional.empty());
        assertThrows(ShowNotFoundException.class, () -> showService.deleteById(2));
    }

    @Test
    @DisplayName("deleteById throws DatabaseException on SQL error")
    void deleteById_throwsDatabaseException() throws Exception {
        when(showDao.findById(1)).thenReturn(Optional.of(new Show()));
        when(showDao.deleteById(1)).thenThrow(new java.sql.SQLException("DB error"));
        assertThrows(DatabaseException.class, () -> showService.deleteById(1));
    }
}
