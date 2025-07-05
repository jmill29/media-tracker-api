
package com.jmill29.tvtrackerapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmill29.tvtrackerapi.dto.UserRequest;
import com.jmill29.tvtrackerapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * REST controller for authentication-related endpoints (e.g., user registration).
 * <p>
 * Provides endpoints for user registration and future authentication features.
 * </p>
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for user registration and authentication.")
public class AuthController {

    private UserService userService;


    /**
     * Constructs an {@code AuthController} with the required {@link UserService}.
     *
     * @param userService the user service to handle registration logic
     */
    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Registers a new user in the system.
     * <p>
     * Accepts a {@link UserRequest} payload and delegates registration to the service layer.
     * Returns a success message if registration is successful.
     * </p>
     *
     * @param user the user registration request body
     * @return a response entity with a success message
     */
    @Operation(summary = "Register a new user", description = "Creates a new user account with the provided registration details.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User registered successfully.",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
        @ApiResponse(responseCode = "409", description = "User already exists.",
            content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid input or missing required fields.",
            content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestBody UserRequest user) {
        // Delegates registration to the service layer. Throws if user already exists or input is invalid.
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully.");
    }

}
