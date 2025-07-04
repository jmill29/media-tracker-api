package com.jmill29.tvtrackerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown to indicate that no TV shows were found in the database or as a result of a query.
 * <p>
 * This exception is typically used in the service or DAO layers when a search or retrieval operation
 * yields no results for TV shows. It is annotated with {@link ResponseStatus}
 * to automatically return a 404 NOT_FOUND HTTP status when thrown in a Spring web context.
 * </p>
 *
 * <p>Usage examples include:</p>
 * <ul>
 *   <li>When a user requests a list of shows and the database is empty.</li>
 *   <li>When a search filter returns no matching shows.</li>
 * </ul>
 *
 * <p>This helps provide a clear and consistent error response to API consumers.</p>
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoShowsFoundException extends RuntimeException {

    /**
     * Constructs a new {@code NoShowsFoundException} with a default message indicating no shows were found.
     */
    public NoShowsFoundException() {
        super("No shows found");
    }

    /**
     * Constructs a new {@code NoShowsFoundException} with the specified detail message.
     *
     * @param message the detail message explaining the context of the error
     */
    public NoShowsFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code NoShowsFoundException} with the specified detail message and cause.
     *
     * @param message the detail message explaining the context of the error
     * @param cause   the underlying cause of the exception (can be {@code null})
     */
    public NoShowsFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code NoShowsFoundException} with the specified cause.
     *
     * @param cause the underlying cause of the exception (can be {@code null})
     */
    public NoShowsFoundException(Throwable cause) {
        super(cause);
    }
}
