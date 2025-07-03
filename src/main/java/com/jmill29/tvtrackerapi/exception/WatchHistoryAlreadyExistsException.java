package com.jmill29.tvtrackerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a watch history entry already exists for a user and show.
 * Results in a 409 Conflict HTTP response.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class WatchHistoryAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new exception with a default message.
     */
    public WatchHistoryAlreadyExistsException() {
        super("Watch history entry already exists for this user and show");
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message
     */
    public WatchHistoryAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public WatchHistoryAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     * @param cause the cause
     */
    public WatchHistoryAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}
