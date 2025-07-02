package com.jmill29.tvtrackerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmill29.tvtrackerapi.exception.NoShowsFoundException;
import com.jmill29.tvtrackerapi.model.Show;
import com.jmill29.tvtrackerapi.service.ShowService;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    @Autowired
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    // Define endpoints for show-related operations here
    @GetMapping
    public List<Show> getAllShows() {
        List<Show> shows = showService.findAll();
        if (shows.isEmpty()) {
            throw new NoShowsFoundException("No shows found in the database.");
        }

        return shows;
    }
}
