package com.jmill29.tvtrackerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Exception thrown when no watch history is found for the specified user.
 * Results in a 404 Not Found HTTP response.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class WatchHistoryNotFoundException extends RuntimeException {

    /**
     * Constructs a new exception with a default message.
     */
    public WatchHistoryNotFoundException() {
        super("No watch history found for the specified user");
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message
     */
    public WatchHistoryNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public WatchHistoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     * @param cause the cause
     */
    public WatchHistoryNotFoundException(Throwable cause) {
        super(cause);
    }

}
