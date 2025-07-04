package com.jmill29.tvtrackerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jmill29.tvtrackerapi.dto.ErrorResponse;

/**
 * Global exception handler for the TV Tracker API application.
 * <p>
 * This class handles custom and common exceptions thrown by controllers and services,
 * returning a standardized {@link com.jmill29.tvtrackerapi.dto.ErrorResponse} with appropriate HTTP status codes.
 * </p>
 *
 * <ul>
 *   <li>{@link UserAlreadyExistsException} - 409 CONFLICT</li>
 *   <li>{@link DatabaseException} - 500 INTERNAL_SERVER_ERROR</li>
 *   <li>{@link NoShowsFoundException} - 404 NOT_FOUND</li>
 *   <li>{@link UserNotFoundException} - 404 NOT_FOUND</li>
 *   <li>{@link WatchHistoryNotFoundException} - 404 NOT_FOUND</li>
 *   <li>{@link WatchHistoryAlreadyExistsException} - 409 CONFLICT</li>
 *   <li>{@link IllegalArgumentException} - 400 BAD_REQUEST</li>
 * </ul>
 *
 * <p>All responses include a timestamp and error message.</p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link UserAlreadyExistsException} and returns a 409 CONFLICT response.
     */
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleException(UserAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            ex.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handles {@link DatabaseException} and returns a 500 INTERNAL_SERVER_ERROR response.
     */
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErrorResponse> handleException(DatabaseException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles {@link NoShowsFoundException} and returns a 404 NOT_FOUND response.
     */
    @ExceptionHandler(NoShowsFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(NoShowsFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link UserNotFoundException} and returns a 404 NOT_FOUND response.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link WatchHistoryNotFoundException} and returns a 404 NOT_FOUND response.
     */
    @ExceptionHandler(WatchHistoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(WatchHistoryNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link WatchHistoryAlreadyExistsException} and returns a 409 CONFLICT response.
     */
    @ExceptionHandler(WatchHistoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleException(WatchHistoryAlreadyExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            ex.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handles {@link IllegalArgumentException} and returns a 400 BAD_REQUEST response.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
