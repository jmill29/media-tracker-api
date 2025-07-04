package com.jmill29.tvtrackerapi.exception;

/**
 * Exception thrown to indicate that a show already exists in the database or system.
 * <p>
 * This exception is typically used in the service or DAO layers when attempting to add a show
 * that would violate a uniqueness constraint, either globally or per user.
 * </p>
 *
 * <p>Usage examples include:</p>
 * <ul>
 *   <li>When trying to add a show with a title that already exists in the database.</li>
 *   <li>When a user attempts to add a show to their watchlist that is already present.</li>
 * </ul>
 *
 * <p>This helps provide a clear and consistent error response to API consumers.</p>
 */
public class ShowAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new {@code ShowAlreadyExistsException} with a default message indicating the show already exists.
     */
    public ShowAlreadyExistsException() {
        super("Show already exists");
    }

    /**
     * Constructs a new {@code ShowAlreadyExistsException} with the specified detail message.
     *
     * @param message the detail message explaining the context of the error
     */
    public ShowAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ShowAlreadyExistsException} with the specified detail message and cause.
     *
     * @param message the detail message explaining the context of the error
     * @param cause   the underlying cause of the exception (can be {@code null})
     */
    public ShowAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code ShowAlreadyExistsException} with the specified cause.
     *
     * @param cause the underlying cause of the exception (can be {@code null})
     */
    public ShowAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
