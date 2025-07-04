package com.jmill29.tvtrackerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown to indicate that a user could not be found in the system.
 * <p>
 * This exception is typically used in the service or DAO layers when a user lookup by ID, username,
 * or other unique identifier fails.
 * </p>
 *
 * <p>Usage examples include:</p>
 * <ul>
 *   <li>When a user requests their profile but no matching user is found.</li>
 *   <li>When attempting to update or delete a user that does not exist.</li>
 * </ul>
 *
 * <p>This helps provide a clear and consistent error response to API consumers.</p>
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code UserNotFoundException} with a default message indicating the user was not found.
     */
    public UserNotFoundException() {
        super("User not found");
    }

    /**
     * Constructs a new {@code UserNotFoundException} with the specified detail message.
     *
     * @param message the detail message explaining the context of the error
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code UserNotFoundException} with the specified detail message and cause.
     *
     * @param message the detail message explaining the context of the error
     * @param cause   the underlying cause of the exception (can be {@code null})
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code UserNotFoundException} with the specified cause.
     *
     * @param cause the underlying cause of the exception (can be {@code null})
     */
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
