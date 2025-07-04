package com.jmill29.tvtrackerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown to indicate that a user already exists in the system.
 * <p>
 * This exception is typically used in the service or DAO layers when attempting to create a user
 * with a username, email, or other unique identifier that already exists in the database.
 * </p>
 *
 * <p>Usage examples include:</p>
 * <ul>
 *   <li>When registering a new user with an email that is already taken.</li>
 *   <li>When adding a user to a group or system where the user already exists.</li>
 * </ul>
 *
 * <p>This helps provide a clear and consistent error response to API consumers.</p>
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new {@code UserAlreadyExistsException} with a default message indicating the user already exists.
     */
    public UserAlreadyExistsException() {
        super("User already exists");
    }

    /**
     * Constructs a new {@code UserAlreadyExistsException} with the specified detail message.
     *
     * @param message the detail message explaining the context of the error
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code UserAlreadyExistsException} with the specified detail message and cause.
     *
     * @param message the detail message explaining the context of the error
     * @param cause   the underlying cause of the exception (can be {@code null})
     */
    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code UserAlreadyExistsException} with the specified cause.
     *
     * @param cause the underlying cause of the exception (can be {@code null})
     */
    public UserAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
