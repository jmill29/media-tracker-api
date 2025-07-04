package com.jmill29.tvtrackerapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jmill29.tvtrackerapi.dto.ErrorResponse;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryRequest;
import com.jmill29.tvtrackerapi.dto.UserWatchHistoryResponse;
import com.jmill29.tvtrackerapi.service.UserWatchHistoryService;
import com.jmill29.tvtrackerapi.utils.AuthUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for managing the watch history of a user.
 * <p>
 * Provides endpoints to retrieve, add, update, and delete watch history records
 * associated with the authenticated user. All endpoints require a valid Authorization header.
 * </p>
 */
@Tag(name = "User Watch History", description = "Operations related to user watch history management")
@RestController
@RequestMapping("/api/watch-history")
public class UserWatchHistoryController {

    private final UserWatchHistoryService userWatchHistoryService;

    

    /**
     * Constructs a UserWatchHistoryController with the required service.
     *
     * @param userWatchHistoryService the service for user watch history operations
     */
    @Autowired
    public UserWatchHistoryController(UserWatchHistoryService userWatchHistoryService) {
        this.userWatchHistoryService = userWatchHistoryService;
    }

    // Define endpoints for user watch history operations here

    /**
     * Retrieves the watch history for the authenticated user.
     *
     * @param getAll if true, returns all records; if false, only non-empty watch history
     * @param authHeader the Bearer token for authentication
     * @return a list of UserWatchHistoryResponse objects representing the user's watch history
     */
    @Operation(
        summary = "Get watch history for the authenticated user",
        description = "Returns the watch history for the user identified by the Authorization header."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Watch history retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserWatchHistoryResponse.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "No watch history found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<UserWatchHistoryResponse>> getWatchHistoryByUsername(
        @Parameter(description = "If true, returns all records; if false, only non-empty watch history", example = "false")
        @RequestParam(required = false, defaultValue = "false") boolean getAll,
        @Parameter(description = "Bearer token for authentication", required = true)
        @RequestHeader("Authorization") String authHeader) {
        List<UserWatchHistoryResponse> watchHistory = userWatchHistoryService.getWatchHistoryByUsername(
            AuthUtil.extractUsernameFromAuthHeader(authHeader),
            getAll
        );
        return ResponseEntity.ok(watchHistory);
    }


    /**
     * Adds a show to the authenticated user's watch history.
     *
     * @param userWatchHistoryRequest the request body containing showId and status
     * @param authHeader the Bearer token for authentication
     * @return a ResponseEntity containing a success message if the show was added
     */
    @Operation(
        summary = "Add a show to the authenticated user's watch history",
        description = "Adds a show to the user's watch history."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Show added successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request body or parameters",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "409", description = "Show already in watch history",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<String> addShowToWatchHistory(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Request body containing showId and status",
            required = true,
            content = @Content(schema = @Schema(implementation = UserWatchHistoryRequest.class))
        )
        @RequestBody UserWatchHistoryRequest userWatchHistoryRequest,
        @Parameter(description = "Bearer token for authentication", required = true)
        @RequestHeader("Authorization") String authHeader) {
        userWatchHistoryService.addShowToWatchHistory(
            userWatchHistoryRequest,
            AuthUtil.extractUsernameFromAuthHeader(authHeader)
        );
        return ResponseEntity.ok("Show added to watch history successfully.");
    }


    /**
     * Updates the watch status for a show in the authenticated user's watch history.
     *
     * @param userWatchHistoryRequest the request body containing showId and new status
     * @param authHeader the Bearer token for authentication
     * @return a ResponseEntity containing a success message if the watch status was updated
     */
    @Operation(
        summary = "Update the watch status for a show in the authenticated user's watch history",
        description = "Updates the watch status for a show in the user's watch history."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Watch status updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request body or parameters",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Watch history entry not found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping
    public ResponseEntity<String> updateWatchStatus(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Request body containing showId and new status",
            required = true,
            content = @Content(schema = @Schema(implementation = UserWatchHistoryRequest.class))
        )
        @RequestBody UserWatchHistoryRequest userWatchHistoryRequest,
        @Parameter(description = "Bearer token for authentication", required = true)
        @RequestHeader("Authorization") String authHeader) {
        userWatchHistoryService.updateWatchStatus(
            userWatchHistoryRequest,
            AuthUtil.extractUsernameFromAuthHeader(authHeader)
        );
        return ResponseEntity.ok("Watch status updated successfully.");
    }


    /**
     * Deletes a show from the authenticated user's watch history by show ID.
     *
     * @param showId the ID of the show to remove
     * @param authHeader the Bearer token for authentication
     * @return a ResponseEntity containing a success message if the show was removed
     */
    @Operation(
        summary = "Delete a show from the authenticated user's watch history",
        description = "Deletes a show from the user's watch history by show ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Show removed successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid show ID",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "401", description = "Unauthorized",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Show not found in watch history",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{showId}")
    public ResponseEntity<String> deleteShowFromWatchHistory(
        @Parameter(description = "ID of the show to remove", required = true, example = "123")
        @PathVariable int showId,
        @Parameter(description = "Bearer token for authentication", required = true)
        @RequestHeader("Authorization") String authHeader) {
        userWatchHistoryService.deleteShowFromWatchHistory(
            AuthUtil.extractUsernameFromAuthHeader(authHeader),
            showId
        );
        return ResponseEntity.ok("Show removed from watch history successfully.");
    }
}
