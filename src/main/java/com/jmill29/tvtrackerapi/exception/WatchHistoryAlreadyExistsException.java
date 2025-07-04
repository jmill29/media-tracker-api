package com.jmill29.tvtrackerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown to indicate that a watch history entry already exists for a specific user and show.
 * <p>
 * This exception is typically used in the service or DAO layers when attempting to add a watch history
 * record that violates a uniqueness constraint on the user-show combination.
 * </p>
 *
 * <p>Usage examples include:</p>
 * <ul>
 *   <li>When a user tries to mark a show as watched that is already in their watch history.</li>
 *   <li>When attempting to create a duplicate watch history entry for the same user and show.</li>
 * </ul>
 *
 * <p>This helps provide a clear and consistent error response to API consumers.</p>
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class WatchHistoryAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new {@code WatchHistoryAlreadyExistsException} with a default message indicating the entry already exists.
     */
    public WatchHistoryAlreadyExistsException() {
        super("Watch history entry already exists for this user and show");
    }

    /**
     * Constructs a new {@code WatchHistoryAlreadyExistsException} with the specified detail message.
     *
     * @param message the detail message explaining the context of the error
     */
    public WatchHistoryAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code WatchHistoryAlreadyExistsException} with the specified detail message and cause.
     *
     * @param message the detail message explaining the context of the error
     * @param cause   the underlying cause of the exception (can be {@code null})
     */
    public WatchHistoryAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code WatchHistoryAlreadyExistsException} with the specified cause.
     *
     * @param cause the underlying cause of the exception (can be {@code null})
     */
    public WatchHistoryAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
