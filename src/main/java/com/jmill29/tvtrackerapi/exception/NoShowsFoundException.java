package com.jmill29.tvtrackerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when no shows are found in the database.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoShowsFoundException extends RuntimeException {

    /**
     * Constructs a new exception with a default message.
     */
    public NoShowsFoundException() {
        super("No shows found");
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message
     */
    public NoShowsFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public NoShowsFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     * @param cause the cause
     */
    public NoShowsFoundException(Throwable cause) {
        super(cause);
    }

}
