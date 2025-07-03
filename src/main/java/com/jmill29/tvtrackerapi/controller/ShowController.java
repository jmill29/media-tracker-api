package com.jmill29.tvtrackerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmill29.tvtrackerapi.exception.NoShowsFoundException;
import com.jmill29.tvtrackerapi.model.Show;
import com.jmill29.tvtrackerapi.service.ShowService;


/**
 * REST controller for handling show-related API requests.
 * Provides endpoints for retrieving TV show data.
 */
@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;


    /**
     * Constructs a ShowController with the given ShowService.
     * @param showService the service for show operations
     */
    @Autowired
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    // Define endpoints for show-related operations here
    /**
     * Retrieves all shows from the database.
     * @return a list of all shows
     * @throws NoShowsFoundException if no shows are found
     */
    @GetMapping
    public List<Show> getAllShows() {
        return showService.findAll();
    }
}
