package com.jmill29.tvtrackerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmill29.tvtrackerapi.dto.ErrorResponse;
import com.jmill29.tvtrackerapi.exception.NoShowsFoundException;
import com.jmill29.tvtrackerapi.model.Show;
import com.jmill29.tvtrackerapi.service.ShowService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * REST controller for handling show-related API requests.
 * Provides endpoints for retrieving TV show data.
 */
@Tag(name = "Shows", description = "Operations related to TV show data retrieval")
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
     * <p>This is a public endpoint and does not require authentication.</p>
     * 
     * @return a ResponseEntity containing a list of all shows
     * @throws NoShowsFoundException if no shows are found
     */
    @Operation(
        summary = "Get all shows",
        description = "Retrieves all TV shows from the database. This is a public endpoint and does not require authentication."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Shows retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Show.class, type = "array"))),
        @ApiResponse(responseCode = "404", description = "No shows found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<Show>> getAllShows() {
        return ResponseEntity.ok(showService.findAll());
    }
}
