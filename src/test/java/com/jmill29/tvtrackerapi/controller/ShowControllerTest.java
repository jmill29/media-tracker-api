package com.jmill29.tvtrackerapi.controller;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.jmill29.tvtrackerapi.model.Show;
import com.jmill29.tvtrackerapi.service.ShowService;

@DisplayName("ShowController Test")
class ShowControllerTest {
    @Mock
    private ShowService showService;
    @InjectMocks
    private ShowController showController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getAllShows returns a list of shows")
    void getAllShows_returnsList() {
        List<Show> shows = List.of(new Show());
        when(showService.findAll()).thenReturn(shows);
        ResponseEntity<List<Show>> response = showController.getAllShows();
        assertEquals(200, response.getStatusCode().value());
        assertEquals(shows, response.getBody());
    }

    @Test
    @DisplayName("getAllShows returns empty list")
    void getAllShows_returnsEmptyList() {
        List<Show> shows = List.of();
        when(showService.findAll()).thenReturn(shows);
        ResponseEntity<List<Show>> response = showController.getAllShows();
        assertEquals(200, response.getStatusCode().value());
        assertEquals(shows, response.getBody());
    }

    @Test
    @DisplayName("getAllShows throws exception on service error")
    void getAllShows_serviceThrowsException() {
        when(showService.findAll()).thenThrow(new RuntimeException("DB error"));
        RuntimeException ex = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () ->
            showController.getAllShows()
        );
        assertEquals("DB error", ex.getMessage());
    }
}
