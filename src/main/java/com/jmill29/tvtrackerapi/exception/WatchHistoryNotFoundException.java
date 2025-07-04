package com.jmill29.tvtrackerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown to indicate that no watch history was found for the specified user.
 * <p>
 * This exception is typically used in the service or DAO layers when a lookup for a user's watch history
 * yields no results.
 * </p>
 *
 * <p>Usage examples include:</p>
 * <ul>
 *   <li>When a user requests their watch history and none exists.</li>
 *   <li>When searching for a watch history entry by user and show, but none is found.</li>
 * </ul>
 *
 * <p>This helps provide a clear and consistent error response to API consumers.</p>
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class WatchHistoryNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code WatchHistoryNotFoundException} with a default message indicating no watch history was found.
     */
    public WatchHistoryNotFoundException() {
        super("No watch history found for the specified user");
    }

    /**
     * Constructs a new {@code WatchHistoryNotFoundException} with the specified detail message.
     *
     * @param message the detail message explaining the context of the error
     */
    public WatchHistoryNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code WatchHistoryNotFoundException} with the specified detail message and cause.
     *
     * @param message the detail message explaining the context of the error
     * @param cause   the underlying cause of the exception (can be {@code null})
     */
    public WatchHistoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code WatchHistoryNotFoundException} with the specified cause.
     *
     * @param cause the underlying cause of the exception (can be {@code null})
     */
    public WatchHistoryNotFoundException(Throwable cause) {
        super(cause);
    }
}
